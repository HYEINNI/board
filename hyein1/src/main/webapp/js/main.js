$(function(){
    pageList(1);
    graphList(1);
});

function onclickLimit(){
	pageList(1);
}

// 검색 조건 예외처리
function pageList(page){
    // 예외처리
    if($('#searchOption').val() != "" && $('#searchOption').val() != null){ 
        if($('#keyword').val() == null || $('#keyword').val()=="") {
            alert("검색할 내용을 작성해주시오.");
            $('#keyword').focus(); 
            return;
        } 
    } else {    
        if($('#keyword').val() != null && $('#keyword').val() != ""){         
            alert("검색조건을 선택해주시오");
            $('#searchOption').focus();       
            return;
        }
    }

    $.ajax({
        url : "/paging.do",
        type : "GET",
        data : {
        	searchOption : $('#searchOption').val(),
            keyword : $('#keyword').val(),
            limit: $('#limit').val() ,
            "page" :page,
           
        },
        success : function(res){
            console.log("성공");
            $('#grid').empty();
            $('#pageNum').empty();
            $('#chart-area').empty();

            var grid = new tui.Grid({
                el: document.getElementById('grid'),
                scrollX: false,
                scrollY: false,
                columns : [
                    {header: '번호', name: 'board_no', align: 'center'},
                    {header: '제목', name: 'title', align: 'center'},
                    {header: '작성자', name: 'writer', align: 'center'},
                    {header: '날짜', name: 'regdate', align: 'center'},
                    {header: '조회수', name: 'hitnum', align: 'center'},
                ]
            });

            // board_no 클릭 시 detail 페이지 이동
            grid.on('click', (ev) => {
                if(ev.columnName != 'board_no' ){
                    return;
                }
                window.location.href = `/detail.do?boardNo=` + grid.getRow(ev.rowKey).board_no;
            });

            var el = document.getElementById('chart-area');     
            
           // 막대 그래프에 조회수 및 번호 배열로 값을 뽑아
            var hitnum = res.data.map(function(data) {
                return data.hitnum; // 게시글의 조회수를 배열로
            });
  
            var board_no = res.data.map(function(data){
               return data.board_no;
            });
            
            console.log(hitnum);
            console.log(board_no);
            
            
            
	var options = {
		chart: { title: '막대 그래프', width: 1500, height: 400},
		xAxis: { title: '번호' },
		yAxis: { title: '조회수',
			tick: { //눈금
				interval: 10,
			},
			label: { //단위
				interval: 20,
			},
			scale : {
				min: 0,
				max: Math.max(...hitnum),
				stepSize : 1,
			}, 
		}
	};
                  	  
               
                
            // 막대 그래프 데이터 
            var data = {
                categories: board_no,
                series: 
                    [{
                        name: '조회수',
                        data: hitnum
                    }]       
            };

            var chart = toastui.Chart.columnChart({el, data, options});
            
            //grid에 데이터 넣기
            grid.resetData(res.data);

            // paging button 
            var cnt = res.paging.cnt;
            var startBlock = res.paging.startBlock;
            
            if(page != 1){
                var first = 
                    '<button onclick="pageList(1)"> << </button>';                                
                $('#pageNum').append(first);
                
                var prev =
                    '<button onclick="pageList(' + (page-1) + ')"> < </button>';
                $('#pageNum').append(prev);
            }
            
            for(var i=0; i<5; i++){
                var btn = 
                    '<button id="btn' + (startBlock+i) + '" onclick="pageList(' + (startBlock+i) + ')">'+(startBlock+i)+'</button>';
                     
                $("#pageNum").append(btn);
                
                if ((startBlock+i) == page){
                    $("#btn" + (startBlock+i)).css("background", "gray");
                } 
                
                if(startBlock+i == cnt) break;
            }
            
            if(page != cnt){
                var next =
                    '<button onclick="pageList(' + (page+1) + ')"> > </button>';
                $('#pageNum').append(next);
                var last =  
                    '<button onclick="pageList(' + cnt + ')"> >> </button>';
                $('#pageNum').append(last);
            }            
        }
    });
}

function graphList(){
    $.ajax({
        url : "/graph.do",
        type : "GET",  
        success : function(res) {   
        	
        	
           console.log("성공2")
           
           var el = document.getElementById('chart');
           
           var regdate = res.data.map(function(data){ 
               return data.regdate;
            });
          
           var hitnum = res.data.map(function(data) { 
                return data.hitnum; 
            });
  
           console.log(regdate);
           console.log(hitnum);
           
            var data = {
                categories: regdate,
                series: [{
                    name: '조회수',
                    data: hitnum
                }]       
            };
            
            var maxCnt = Math.max(...hitnum);
            
        	var options = {
        			chart: { title: '막대 그래프', width: 1500, height: 400},
        			xAxis: { title: '번호' },
        			yAxis: { title: '조회수',
        				tick: { //눈금
        					interval: 15,
        				},
        				label: { //단위
        					interval: 30,
        				},
        				scale : {
        					min: 0,
        					max: Math.max(...hitnum),
        					stepSize : 1,
        				}, 
        			}
        		};

            var chart = toastui.Chart.lineChart({ el, data, options});
        }
    });
  }

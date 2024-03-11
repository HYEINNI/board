<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>지도 </title>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/ol@v8.2.0/dist/ol.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/ol@v8.2.0/ol.css">
    
   <style>
     #map {  width: 100%;  height: 900px; }
    </style>
    
</head>
<body>
    <h1>지도 띄우기</h1>
    <div id="map"></div>
    <input type="button" value="뒤로가기" onclick="location.href='/main.do'">

    <script>
       var markerLayer; //마커 Layer
       
        //타일 생성
        var vworldBaseLayer = new ol.layer.Tile({
            source: new ol.source.XYZ({
                url: 'https://api.vworld.kr/req/wmts/1.0.0/913C8751-E818-3A6B-A7B7-CB032CEDF671/Base/{z}/{y}/{x}.png',
            }),
            minZoom: 4, //최소 줌
            maxZoom: 20, //최대 줌
           zIndex: 2, //우선순위
           preload: Infinity //지정한 레벨까지 저해상도 타일을 미리 로드
        });

        // 지도 생성
        var map = new ol.Map({
            layers: [vworldBaseLayer],
            target: 'map',
            view: new ol.View({
            	center: ol.proj.fromLonLat([126.95958210178, 37.388333045362]), //초기 지도 위도 경도 설정  *fromLonLat : 위도,경도 =>좌표계
                zoom: 17,
                projection: 'EPSG:3857' //구글 좌표계
            })
        });
        
        var markerSource = new ol.source.Vector();
        
        function addMarker(coordinate){
           var point_feature = new ol.Feature({
              geometry: new ol.geom.Point([coordinate[0], coordinate[1]])
           });
           
           markerSource.addFeature(point_feature);
           
           //마커 스타일
           var markerStyle = new ol.style.Style({
              image: new ol.style.Icon({
                 opacity:1, //투명도
                 scale:1.0,
                 src:'	https://map.vworld.kr/images/maps/marker.png'
              })
           });
           markerLayer = new ol.layer.Vector({
              source: markerSource,
              style: markerStyle
           });
           markerLayer.setZIndex(3); //다른 레이어들 보다 더 앞에 보여주는 기능
           map.addLayer(markerLayer);       
        }
        
        // 지도 클릭 이벤트
           map.on('click', function (event) {
	            var coordinate = event.coordinate;  
	            lon = coordinate[0];
	            lat = coordinate[1];
            
            if(map.hasFeatureAtPixel(event.pixel) === true){
                var features = map.getFeaturesAtPixel(event.pixel);
                if(features){
                    var clickedCoordinate = ol.proj.transform([lon,lat], 'EPSG:3857', 'EPSG:4326');
                    console.log('마커 클릭한 좌표:', clickedCoordinate);      
                }
            } else {
          	 addMarker(coordinate);
            }
        }); 


    </script>
</body>
</html>
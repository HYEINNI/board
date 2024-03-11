package co.kr.hyein.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import co.kr.hyein.service.boardService;
import co.kr.hyein.vo.HyeinVO;

@Controller
public class boardController {
	@Resource(name="boardService")
	private boardService service;
	
	/*list*/
	@RequestMapping(value="/main.do")
	public String main(Model model){
		/*List<HyeinVO> boardList = service.getList();
		model.addAttribute("data", boardList);*/
		return "main";
	}
	
	/*detail*/
	@RequestMapping(value="/detail.do")
	public String detail(Model model, int boardNo){
		model.addAttribute("data", service.boardDetail(boardNo));
		return "/detail";
	}
	
	/*delete*/
	@RequestMapping(value="/delete.do")
	public String delete(int boardNo){
		service.boardDelete(boardNo);
		return "redirect:/main.do";
	}
	
	/*insert*/
	@RequestMapping(value="/insertPage.do")
	public String insertPage(){
		return "insert";
	}
	/*@RequestMapping(value="/insert.do" , method=RequestMethod.POST)
	public String insert(HyeinVO vo){
		service.boardInsert(vo);
		return "redirect:/main.do";
	}*/
	
/*	@RequestMapping(value="/insert.do" , method=RequestMethod.POST)
	public String insert(HyeinVO vo , @RequestParam("file") MultipartFile file ) throws IOException {
	    String fileName = null;
	    		
	    if (!file.isEmpty()) { //file 있을 때
	        String originaFileName = file.getOriginalFilename();
	        String ext = FilenameUtils.getExtension(originaFileName);
	        UUID uuid = UUID.randomUUID();
	        fileName = uuid + "." + ext;
	        file.transferTo(new File("C:/upload/" + fileName));
	        
	        vo.setFileId(uuid.toString());
	        vo.setfName(fileName);
	        vo.setfPath("C:/upload/" + fileName);
	        
	       	        
	        service.fileUpload(vo);     
	    }
	    service.boardInsert(vo);
	    return "redirect:/main.do";
	}*/
	
	@RequestMapping(value="/insert.do" , method=RequestMethod.POST)
	public String insert(HyeinVO vo , @RequestParam("file") MultipartFile file ) throws IOException {
	    service.boardInsert(vo, file);
	    return "redirect:/main.do";
	}
	
	/*update*/
	@RequestMapping(value="updatePage.do")
	public String updatePage(Model model , int boardNo){
		model.addAttribute("data", service.boardDetail(boardNo));
		return "update";
	}
	
	@RequestMapping(value="/update.do" , method=RequestMethod.POST)
	public String update(HyeinVO vo , int boardNo){
		service.boardUpdate(vo);
		return "redirect:/main.do";
	}
	
	/*paging*/
	@RequestMapping(value="/paging.do" , method=RequestMethod.GET)
	public ModelAndView paging(HyeinVO vo){
		ModelAndView mv = new ModelAndView("jsonView");
		
		vo.setTotal(service.totalCnt(vo));
		vo.Calculation(vo);
		
		List<HyeinVO> boardList = service.getList(vo);
		
		mv.addObject("data", boardList);
		mv.addObject("paging", vo);
		
		return mv;
	}
	/*map*/
	 @RequestMapping(value="/map.do")
	   public String map(){
	       return "map";
	   }
	 
	 @RequestMapping(value ="/graph.do" , method=RequestMethod.GET)
	 public ModelAndView grahp(){
		 ModelAndView mv = new ModelAndView("jsonView");
		 List<Map<String, Object>> result = service.graphCnt(); 
		 mv.addObject("data", result);
		 return mv;
		 
	 }
}

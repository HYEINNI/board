package co.kr.hyein.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.kr.hyein.service.boardService;
import co.kr.hyein.vo.HyeinVO;

@Service("boardService")
public class boardServiceImpl implements boardService{
	@Resource(name="boardMapper")
	private boardMapper mapper;

	@Override
	public Object boardDetail(int boardNo) {
		// TODO Auto-generated method stub
		mapper.hitnum(boardNo);
		return mapper.boardDetail(boardNo);
	}

	@Override
	public void boardDelete(int boardNo) {
		// TODO Auto-generated method stub
		mapper.boardDelete(boardNo);
	}

	@Override
	public void boardUpdate(HyeinVO vo) {
		// TODO Auto-generated method stub
		mapper.boardUpdate(vo);
	}

	@Override
	public int totalCnt(HyeinVO vo) {
		// TODO Auto-generated method stub
		return mapper.totalCnt(vo);
	}

	@Override
	public List<HyeinVO> getList(HyeinVO vo) {
		// TODO Auto-generated method stub
		return mapper.getList(vo);
	}

	@Override
	public List<Map<String, Object>> graphCnt() {
		// TODO Auto-generated method stub
		return mapper.graphCnt();
	}

	@Override
	public void fileUpload(HyeinVO vo) {
		// TODO Auto-generated method stub
		mapper.fileUpload(vo);
	}

	@Override
	public void boardInsert(HyeinVO vo, MultipartFile file) throws IllegalStateException, IOException {
		 String fileName = null;
 		
		    if (!file.isEmpty()) { //file 있을 때
		        String originaFileName = file.getOriginalFilename();
		        String ext = FilenameUtils.getExtension(originaFileName);
		        UUID uuid = UUID.randomUUID();
		        fileName = uuid + "." + ext;
		        file.transferTo(new File("C:/upload/" + fileName));
		        
		        vo.setFileId(uuid.toString());
		        vo.setFileName(fileName);
		        vo.setFilePath("C:/upload/" + fileName);
		        
		       	        
		        mapper.fileUpload(vo);     
		    }
		    mapper.boardInsert(vo);
	}

}

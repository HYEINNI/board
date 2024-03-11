package co.kr.hyein.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import co.kr.hyein.vo.HyeinVO;

public interface boardService {

	List<HyeinVO> getList(HyeinVO vo);

	Object boardDetail(int boardNo);

	void boardDelete(int boardNo);

//	int boardInsert(HyeinVO vo);

	void boardUpdate(HyeinVO vo);

	int totalCnt(HyeinVO vo);

	void fileUpload(HyeinVO vo);

	List<Map<String, Object>> graphCnt();

	void boardInsert(HyeinVO vo, MultipartFile file) throws IOException;

}

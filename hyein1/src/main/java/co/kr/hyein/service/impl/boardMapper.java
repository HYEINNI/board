package co.kr.hyein.service.impl;

import java.util.List;
import java.util.Map;

import co.kr.hyein.vo.HyeinVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("boardMapper")
public interface boardMapper {
	List<HyeinVO> getList(HyeinVO vo);
	Object boardDetail(int boardNo);
	void boardDelete(int boardNo);
	int boardInsert(HyeinVO vo);
	void boardUpdate(HyeinVO vo);
	void hitnum(int boardNo);
	int totalCnt(HyeinVO vo);
	/*void fileUpload(HyeinVO vo);*/
	void fileUpload(HyeinVO vo);
	List<Map<String, Object>> graphCnt();
	
}

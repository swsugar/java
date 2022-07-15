package kr.or.ddit.memo.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.memo.MemoVO;

@Repository
public class MemoDAO {
	public static Map<Integer, MemoVO> memoTable = new LinkedHashMap<>();
	public MemoVO selectMemoList(int code) {
		return memoTable.get(code);
	}
	public void insertMemo(int code, MemoVO memo) {
		memoTable.put(code, memo);
	}
	public void updateMemo(int code,MemoVO memo) {
		memoTable.replace(code, memo);
		
	}
	public void deletMemo(int code) {
		memoTable.remove(code);
		
	}
	
}

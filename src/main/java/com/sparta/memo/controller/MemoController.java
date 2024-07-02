package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class MemoController {

    private final Map<Long,Memo> memoList = new HashMap<>(); //Replace DB

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto){ //http 바디에 json 형태로 넘어온다고? >> RequestBody
        //RequestDto -> Entity
        Memo memo = new Memo(requestDto);

        //Memo Max ID Check 중복 방지 > 마지막 값 +1

        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) +1 : 1;
        memo.setId(maxId);

        //DB에 저장
        memoList.put(memo.getId(), memo);

        //Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return memoResponseDto;
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        // Map to List
        List<MemoResponseDto> responseList = memoList.values().stream() //stream : 데이터를 연속적으로 처리 == for문 대체
                .map(MemoResponseDto::new).toList();


        return responseList;
    }
}

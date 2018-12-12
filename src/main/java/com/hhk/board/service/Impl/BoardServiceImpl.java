package com.hhk.board.service.Impl;


import com.hhk.board.domain.Pagination;
import com.hhk.board.domain.SearchVO;
import com.hhk.board.repository.BoardRepository;
import com.hhk.board.service.BoardService;
import com.hhk.board.domain.BoardVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;


    @Override
    public List<BoardVO> List(int nowPage,int total){
        Pagination pager = new Pagination();
        pager.setTotalPage(total);  //총 페이지수를 정해준다.
        pager.setTotalBlock(total); //총 블럭수를 정해준다.
        pager.setNowPage(nowPage); //현재 페이지를 정해 준 후 총 페이지와 현재 페이지를 이용해 스타트 페이지를 정해준다.(setNowPage 안에 setStartPage 가 있음)


        return  boardRepository.List(pager);
    }

    @Override
    public int boardTotal(){

        return boardRepository.boardTotal();
    }

    @Override
    public BoardVO View(int bno){

        return boardRepository.View(bno);
    }

    @Override
    public void Write(BoardVO board){

        boardRepository.Write(board);

    }

    @Override
    public void Update(BoardVO board){

            boardRepository.Update(board);
    }

    @Override
    public void Delete(int bno){

        boardRepository.Delete(bno);
    }

    @Override
    public String getPW(int bno){

        String PW = boardRepository.getPW(bno);
        return PW;
    }

    @Override
    public List<BoardVO> search(SearchVO search,int nowPage,int total){
        Pagination pager = new Pagination();

        pager.setTotalPage(total);  //총 페이지수를 정해준다.
        pager.setTotalBlock(total); //총 블럭수를 정해준다.
        pager.setNowPage(nowPage); //현재 페이지를 정해 준 후 총 페이지와 현재 페이지를 이용해 스타트 페이지를 정해준다.(setNowPage 안에 setStartPage 가 있음)

        String op = search.getOp();

        if(op.equals("title")){
            List<BoardVO> s_result = boardRepository.t_search(search,pager);

            if(s_result==null){

            }
            return s_result;
        }else if(op.equals("content")){
            return boardRepository.c_search(search,pager);
        }else{
            return boardRepository.search(search,pager);
        }
    }

    @Override
    public int searchTotal(SearchVO search){

        String op = search.getOp();
        int s_total=0;


        if(op.equals("title")) {
            s_total = boardRepository.t_searchTotal(search);
        }else if(op.equals("content")){
            s_total = boardRepository.c_searchTotal(search);
        }else{
            s_total = boardRepository.searchTotal(search);
        }


        return s_total;
    }




}

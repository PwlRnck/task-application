package com.crud.tasks.mapper;


import com.crud.tasks.config.Profiles;
import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(Profiles.LOCAL)
public class TrelloMapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1","List1",false);
        TrelloList trelloList2 = new TrelloList("2","List2",false);
        TrelloList trelloList3 = new TrelloList("3","List3",false);
        TrelloList trelloList4 = new TrelloList("4","List4",false);

        List<TrelloList> lists1 = new ArrayList<>();
        List<TrelloList> lists2 = new ArrayList<>();

        lists1.add(trelloList1);
        lists2.add(trelloList2);
        lists2.add(trelloList3);
        lists2.add(trelloList4);

        TrelloBoard trelloBoard1 = new TrelloBoard("1","Board1",lists1);
        TrelloBoard trelloBoard2 = new TrelloBoard("2","Board2",lists2);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);

        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(2,trelloBoardsDto.size());
        assertEquals(1,trelloBoardsDto.get(0).getLists().size());
        assertEquals(3,trelloBoardsDto.get(1).getLists().size());

    }

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloList1 = new TrelloListDto("1","List1",false);
        TrelloListDto trelloList2 = new TrelloListDto("2","List2",false);
        TrelloListDto trelloList3 = new TrelloListDto("3","List3",false);
        TrelloListDto trelloList4 = new TrelloListDto("4","List4",false);

        List<TrelloListDto> lists1 = new ArrayList<>();
        List<TrelloListDto> lists2 = new ArrayList<>();

        lists1.add(trelloList1);
        lists2.add(trelloList2);
        lists2.add(trelloList3);
        lists2.add(trelloList4);

        TrelloBoardDto trelloBoard1 = new TrelloBoardDto("1","Board1",lists1);
        TrelloBoardDto trelloBoard2 = new TrelloBoardDto("2","Board2",lists2);

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoard1);
        trelloBoardsDto.add(trelloBoard2);


        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);

        //Then
        assertEquals(2,trelloBoards.size());
        assertEquals(1,trelloBoards.get(0).getLists().size());
        assertEquals(3,trelloBoards.get(1).getLists().size());

    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloList1 = new TrelloListDto("1","List1",false);
        TrelloListDto trelloList2 = new TrelloListDto("2","List2",false);
        TrelloListDto trelloList3 = new TrelloListDto("3","List3",false);
        TrelloListDto trelloList4 = new TrelloListDto("4","List4",false);

        List<TrelloListDto> lists1 = new ArrayList<>();
        List<TrelloListDto> lists2 = new ArrayList<>();

        lists1.add(trelloList1);
        lists2.add(trelloList2);
        lists2.add(trelloList3);
        lists2.add(trelloList4);

        //When
        List<TrelloList> trelloLists1 = trelloMapper.mapToList(lists1);
        List<TrelloList> trelloLists2 = trelloMapper.mapToList(lists2);

        //Then
        assertEquals(1,trelloLists1.size());
        assertEquals(3,trelloLists2.size());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1","List1",false);
        TrelloList trelloList2 = new TrelloList("2","List2",false);
        TrelloList trelloList3 = new TrelloList("3","List3",false);
        TrelloList trelloList4 = new TrelloList("4","List4",false);

        List<TrelloList> lists1 = new ArrayList<>();
        List<TrelloList> lists2 = new ArrayList<>();

        lists1.add(trelloList1);
        lists2.add(trelloList2);
        lists2.add(trelloList3);
        lists2.add(trelloList4);

        //When
        List<TrelloListDto> trelloLists1 = trelloMapper.mapToListDto(lists1);
        List<TrelloListDto> trelloLists2 = trelloMapper.mapToListDto(lists2);

        //Then
        assertEquals(1,trelloLists1.size());
        assertEquals(3,trelloLists2.size());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("New card","My Trello card","1","1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("New card",trelloCardDto.getName());
        assertEquals("My Trello card",trelloCardDto.getDescription());
        assertEquals("1",trelloCardDto.getPos());
        assertEquals("1",trelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("New card","My Trello card","1","1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto );
        //Then
        assertEquals("New card",trelloCard.getName());
        assertEquals("My Trello card",trelloCard.getDescription());
        assertEquals("1",trelloCard.getPos());
        assertEquals("1",trelloCard.getListId());
    }

}

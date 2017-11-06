package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "my_list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "my_task", trelloLists));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);
        //When
        List<TrelloBoardDto> resultList = trelloService.fetchTrelloBoards();
        //Then
        assertEquals(1, resultList.size());
    }

    @Test
    public void testCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("New card", "My new card", "1", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "New created card", "test.com");
        when(trelloClient.createNewCard(any())).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto resultCard = trelloService.createTrelloCard(trelloCardDto);
        //Then
        assertEquals("1", resultCard.getId());
        assertEquals("New created card", resultCard.getName());
        assertEquals("test.com", resultCard.getShortUrl());
    }
}

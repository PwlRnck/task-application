package com.crud.tasks.controller;


import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    //private TrelloService trelloService;
    private TrelloFacade trelloFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/boards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloFacade.fetchTrelloBoards();
        //return trelloService.fetchTrelloBoards();
        //List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        /*
            trelloBoards.stream().filter(trelloBoardDto -> (trelloBoardDto.getId() != null) && (trelloBoardDto.getName() != null) && (trelloBoardDto.getName().contains("Kodilla")))
                    .forEach(trelloBoardDto -> {

                        System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());

                        System.out.println("This board contains lists: ");

                        trelloBoardDto.getLists().forEach(trelloList ->
                                System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));

                    });
          */
        }

    @RequestMapping(method = RequestMethod.POST, value = "/cards")
    public CreatedTrelloCardDto createdTrelloCardDto(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloFacade.createCard(trelloCardDto);
        //return trelloService.createTrelloCard(trelloCardDto);
    }
}
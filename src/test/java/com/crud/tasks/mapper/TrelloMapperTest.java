package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloMapperTest {

    private final TrelloMapper trelloMapper = new TrelloMapper();

    private final TaskMapper taskMapper = new TaskMapper();

    @Test
    public void shouldMapToBoards() {
        // Arrange
        TrelloBoardDto boardDto1 = new TrelloBoardDto("boardId1", "boardName1", Arrays.asList(
                new TrelloListDto("listId1", "listName1", false),
                new TrelloListDto("listId2", "listName2", true)
        ));
        TrelloBoardDto boardDto2 = new TrelloBoardDto("boardId2", "boardName2", Arrays.asList(
                new TrelloListDto("listId3", "listName3", false),
                new TrelloListDto("listId4", "listName4", true)
        ));
        List<TrelloBoardDto> boardDtoList = Arrays.asList(boardDto1, boardDto2);

        // Act
        List<TrelloBoard> result = trelloMapper.mapToBoards(boardDtoList);

        // Assert
        assertEquals(2, result.size());
        assertEquals("boardId1", result.get(0).getId());
        assertEquals("boardName1", result.get(0).getName());
        assertEquals(2, result.get(0).getLists().size());
        assertEquals("listId1", result.get(0).getLists().get(0).getId());
        assertEquals("listName1", result.get(0).getLists().get(0).getName());
        assertEquals(false, result.get(0).getLists().get(0).isClosed());
        assertEquals("listId2", result.get(0).getLists().get(1).getId());
        assertEquals("listName2", result.get(0).getLists().get(1).getName());
        assertEquals(true, result.get(0).getLists().get(1).isClosed());
        assertEquals("boardId2", result.get(1).getId());
        assertEquals("boardName2", result.get(1).getName());
        assertEquals(2, result.get(1).getLists().size());
        assertEquals("listId3", result.get(1).getLists().get(0).getId());
        assertEquals("listName3", result.get(1).getLists().get(0).getName());
        assertEquals(false, result.get(1).getLists().get(0).isClosed());
        assertEquals("listId4", result.get(1).getLists().get(1).getId());
        assertEquals("listName4", result.get(1).getLists().get(1).getName());
        assertEquals(true, result.get(1).getLists().get(1).isClosed());
    }

    @Test
    public void shouldMapToBoardsDto() {
        // Arrange
        TrelloBoard board1 = new TrelloBoard("boardId1", "boardName1", Arrays.asList(
                new TrelloList("listId1", "listName1", false),
                new TrelloList("listId2", "listName2", true)
        ));
        TrelloBoard board2 = new TrelloBoard("boardId2", "boardName2", Arrays.asList(
                new TrelloList("listId3", "listName3", false),
                new TrelloList("listId4", "listName4", true)
        ));
        List<TrelloBoard> boardList = Arrays.asList(board1, board2);

        // Act
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(boardList);

        // Assert
        assertEquals(2, result.size());
        assertEquals("boardId1", result.get(0).getId());
        assertEquals("boardName1", result.get(0).getName());
        assertEquals(2, result.get(0).getLists().size());
        assertEquals("listId1", result.get(0).getLists().get(0).getId());
        assertEquals("listName1", result.get(0).getLists().get(0).getName());
        assertEquals(false, result.get(0).getLists().get(0).isClosed());
        assertEquals("listId2", result.get(0).getLists().get(1).getId());
        assertEquals("listName2", result.get(0).getLists().get(1).getName());
        assertEquals(true, result.get(0).getLists().get(1).isClosed());
        assertEquals("boardId2", result.get(1).getId());
        assertEquals("boardName2", result.get(1).getName());
        assertEquals(2, result.get(1).getLists().size());
        assertEquals("listId3", result.get(1).getLists().get(0).getId());
        assertEquals("listName3", result.get(1).getLists().get(0).getName());
        assertEquals(false, result.get(1).getLists().get(0).isClosed());
        assertEquals("listId4", result.get(1).getLists().get(1).getId());
        assertEquals("listName4", result.get(1).getLists().get(1).getName());
        assertEquals(true, result.get(1).getLists().get(1).isClosed());
    }

    @Test
    public void testMapToListDto() {
        // Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("listId1", "List 1", false));
        trelloLists.add(new TrelloList("listId2", "List 2", true));

        // When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        // Then
        assertEquals(2, trelloListDtos.size());

        TrelloListDto trelloListDto1 = trelloListDtos.get(0);
        assertEquals("listId1", trelloListDto1.getId());
        assertEquals("List 1", trelloListDto1.getName());
        assertEquals(false, trelloListDto1.isClosed());

        TrelloListDto trelloListDto2 = trelloListDtos.get(1);
        assertEquals("listId2", trelloListDto2.getId());
        assertEquals("List 2", trelloListDto2.getName());
        assertEquals(true, trelloListDto2.isClosed());
    }

    @Test
    public void testMapToCardDto() {
        // Given
        TrelloCard trelloCard = new TrelloCard("Test Card", "Card description", "top", "listId");

        // When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        // Then
        assertEquals("Test Card", trelloCardDto.getName());
        assertEquals("Card description", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("listId", trelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test Card", "Card description", "top", "listId");

        // When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        // Then
        assertEquals("Test Card", trelloCard.getName());
        assertEquals("Card description", trelloCard.getDescription());
        assertEquals("top", trelloCard.getPos());
        assertEquals("listId", trelloCard.getListId());
    }
}




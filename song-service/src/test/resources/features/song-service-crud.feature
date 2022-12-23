Feature: SongService CRUD
  Scenario: client makes call to GET /songs/{id}
    When the client calls GET /songs/1
    Then the client receives status code of 200
    And the client receives:
      | id | name  | artist | album | length | resourceId | year |
      | 1  | test1 | test2  | test3 | test4  |      5     |   6  |
  Scenario: client makes call to DELETE /songs/{id}
    When the client calls DELETE /songs with id= "1"
    Then the client receives status code of 200
    And the client receives 'ids' "1"
SET client_encoding = 'UTF8';

CREATE TABLE song_metadata (
    id serial PRIMARY KEY,
    name text NOT NULL,
    artist text NOT NULL,
    album text NOT NULL,
    length text NOT NULL,
    resource_id integer NOT NULL,
    year integer NOT NULL
);
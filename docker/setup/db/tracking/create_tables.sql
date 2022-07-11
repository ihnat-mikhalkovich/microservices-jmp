SET client_encoding = 'UTF8';

CREATE TABLE public.resource_tracking (
    resource_id serial PRIMARY KEY,
    status text NOT NULL
);

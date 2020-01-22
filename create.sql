CREATE DATABASE movie_api;
\c movie_api;

CREATE TABLE movie (id SERIAL PRIMARY KEY, title VARCHAR, releaseyear INT, writer VARCHAR, duration INT, summary VARCHAR, review VARCHAR);

CREATE TABLE category (id SERIAL PRIMARY KEY, name VARCHAR, description VARCHAR);

CREATE TABLE movie_category (id SERIAL PRIMARY KEY, movieid INT, categoryid INT);

CREATE DATABASE movie_api_test WITH TEMPLATE movie_api;
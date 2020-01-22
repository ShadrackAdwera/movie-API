SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS movie (id SERIAL PRIMARY KEY auto_increment, title VARCHAR, releaseyear INT, writer VARCHAR, duration INT, summary INT, review VARCHAR);

CREATE TABLE IF NOT EXISTS category (id SERIAL PRIMARY KEY auto_increment, name VARCHAR, description VARCHAR);

CREATE TABLE IF NOT EXISTS movie_category (id SERIAL PRIMARY KEY, movieid, categoryid);
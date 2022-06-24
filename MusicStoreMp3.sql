Create database MusicStore
use MusicStore

Create table genre
(
id int primary key identity(1,1),
name varchar(50),
)

Create table artist
(
id int primary key identity(1,1),
name varchar(50),
description varchar(50),
image varchar(200)
)

Create table album
(
id int primary key identity(1,1),
name varchar(50),
artist_id int foreign key references artist(id),
release_date date,
image varchar(200)
)

Create table song
(
id int primary key identity(1,1),
name varchar(50),
media varchar(500),
album_id int foreign key references album(id),
artist_id int foreign key references artist(id),
lyric ntext,
genre_id int foreign key references genre(id),
[view] int,
video varchar(500)
)
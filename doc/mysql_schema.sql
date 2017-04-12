 drop table recommendation;
 create table recommendation(
 		count int,
 		genres varchar(500),
 		movieId int,
 		title varchar(500),
 		average float,
 		userId int,
 		prediction float,
 		rank int
 );
 DELETE * FROM recommendation WHERE userId = 0;
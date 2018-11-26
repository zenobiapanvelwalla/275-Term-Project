SELECT * FROM netflix.users_activities;
/*top ten movies*/
select * from movies where id IN (select movie_id from users_activities where TIMESTAMPDIFF(HOUR,updated_at, now())<=24 GROUP BY movie_id order by count(*) DESC ) LIMIT 10;
select movie_id,count(*) as playCount from users_activities where TIMESTAMPDIFF(HOUR,updated_at, now())<=24 GROUP BY movie_id order by playCount DESC LIMIT 10;
select movie_id, count(*) as playCount from users_activities where DATEDIFF(now(),updated_at)<=7 GROUP BY movie_id ORDER BY playCount DESC LIMIT 10;

select movie_id, count(*) as playCount from users_activities where DATEDIFF(now(),updated_at)<=30 GROUP BY movie_id ORDER BY playCount DESC LIMIT 10;



/* per movie */
select count(*)  from users_activities where movie_id=1 and TIMESTAMPDIFF(HOUR,updated_at, now())<=24;

select count(*) from users_activities where movie_id=1 and DATEDIFF(now(),updated_at)<=7;

select count(*) from users_activities where movie_id=1 and DATEDIFF(now(),updated_at)<=7;


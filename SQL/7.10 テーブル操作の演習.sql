-- 7.8 テーブル操作の演習

-- 演習1
-- (1) 6の演習のテーブルにデータを挿入
insert into fruits (id, name, price, season) values (1, 'apple', 100, 'winter');
insert into fruits (id, name, price, season) values (2, 'grape', 500, 'autumn');
insert into fruits (id, name, price, season) values (3, 'orange', 298, 'summer');

-- (2)全てのレコードとカラムを取得
select * from fruits;

-- (3)品番の大きい順にすべて取得する
select * from fruits order by id desc;

-- (4)「NAME」に「p」を含むレコードを取得
select * from fruits where name like '%p%';

-- (5)レコードの件数を取得
select count(*) from fruits;

-- (6)appleの値段を200に変更
update fruits set price = 200 where name = 'apple';

-- (7)orangeのレコードを削除
delete from fruits where name = 'orange';

-- 演習2
create table colors (id integer, color varchar(30));
insert into colors (id, color) values (1, 'red');
insert into colors (id, color) values (2, 'purple');

select fruits.id, fruits.name, fruits.price, fruits.season, colors.color from fruits join colors on fruits.id = colors.id;

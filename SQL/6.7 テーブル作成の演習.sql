-- 6.7 テーブル作成の演習

-- (1)テーブルを作成
create table fruits (id integer, name varchar(20), price integer);

-- (2)テーブルに新しく「季節」を追加
alter table fruits add column season varchar(10);

-- (3)テーブル名を変更(_newをつける)
alter table fruits rename to fruits_new;

-- (4)名前を変更したテーブルを削除
drop table fruits_new;

-- (5) (1)で作成したテーブルを再度作成
create table fruits (id integer, name varchar(20), price integer, season varchar(10));

/**
 * Author:  marcin
 * Created: May 4, 2021
 */
create table TASK_LIST (
    id int unsigned primary key auto_increment,
    name varchar(100) not null,
    done boolean
);


create table command (
  commandsid                integer not null,
  commandstype              varchar(255),
  roomnumber                varchar(255),
  status                    varchar(255),
  mark                      varchar(255),
  constraint pk_command primary key (commandsid))
;

create sequence command_seq;




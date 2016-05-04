    drop table Club if exists;

    drop table Race if exists;

    drop table Regatta if exists;

    drop table Result if exists;

    drop table Skipper if exists;

    create table Club (
        id decimal(13,0) generated by default as identity,
        adress varchar(255),
        name varchar(255),
        short_name varchar(255) not null unique,
        primary key (id)
    );

    create table Race (
        id decimal(13,0) generated by default as identity,
        end_time varchar(255),
        number integer not null,
        start_time varchar(255),
        regatta_id decimal(13,0),
        primary key (id),
        unique (regatta_id, number)
    );

    create table Regatta (
        id decimal(13,0) generated by default as identity,
        buoyages integer not null,
        end_date timestamp,
        finished boolean not null,
        name varchar(255) not null unique,
        short_name varchar(255) not null unique,
        start_date timestamp,
        primary key (id)
    );

    create table Result (
        id decimal(13,0) generated by default as identity,
        placement varchar(255) not null,
        points integer not null,
        race_id decimal(13,0),
        skipper_id decimal(13,0),
        primary key (id),
        unique (race_id, placement)
    );

    create table Skipper (
        id decimal(13,0) generated by default as identity,
        age_group varchar(255),
        birth_day timestamp,
        catering boolean not null,
        entry_fee boolean,
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        late_registration boolean,
        lunch boolean not null,
        sail_number varchar(255),
        gender varchar(255),
        club_id decimal(13,0),
        regatta_id decimal(13,0),
        primary key (id)
    );

    alter table Race 
        add constraint FK26BEF1E2D31A97 
        foreign key (regatta_id) 
        references Regatta;

    alter table Result 
        add constraint FK91B2B43D235B8BDD 
        foreign key (race_id) 
        references Race;

    alter table Result 
        add constraint FK91B2B43D719E2E57 
        foreign key (skipper_id) 
        references Skipper;

    alter table Skipper 
        add constraint FKE337FF1E1D9905BD 
        foreign key (club_id) 
        references Club;

    alter table Skipper 
        add constraint FKE337FF1EE2D31A97 
        foreign key (regatta_id) 
        references Regatta;

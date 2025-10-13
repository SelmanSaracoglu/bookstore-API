create table if not exists roles (
  id bigint primary key auto_increment,
  name varchar(64) not null unique
) engine=innodb;

create table if not exists permissions (
  id bigint primary key auto_increment,
  code varchar(64) not null unique
) engine=innodb;

-- NOT: users tablonun adı/id tipi projende nasılsa FK tiplerini ona göre kullanıyoruz (genelde BIGINT).
create table if not exists user_roles (
  user_id bigint not null,
  role_id bigint not null,
  primary key (user_id, role_id),
  constraint fk_user_roles_user foreign key (user_id) references users(id),
  constraint fk_user_roles_role foreign key (role_id) references roles(id)
) engine=innodb;

create table if not exists role_permissions (
  role_id bigint not null,
  permission_id bigint not null,
  primary key (role_id, permission_id),
  constraint fk_rp_role foreign key (role_id) references roles(id),
  constraint fk_rp_perm foreign key (permission_id) references permissions(id)
) engine=innodb;


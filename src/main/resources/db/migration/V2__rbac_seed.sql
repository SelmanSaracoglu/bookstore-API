insert ignore into roles (name) values ('ADMIN');
insert ignore into roles (name) values ('USER');

insert ignore into permissions (code) values ('BOOK:READ');
insert ignore into permissions (code) values ('BOOK:WRITE');

-- ADMIN -> tüm permissionlar
insert ignore into role_permissions (role_id, permission_id)
select r.id, p.id
from roles r
join permissions p
where r.name = 'ADMIN';

-- USER -> sadece READ
insert ignore into role_permissions (role_id, permission_id)
select r.id, p.id
from roles r
join permissions p on p.code = 'BOOK:READ'
where r.name = 'USER';

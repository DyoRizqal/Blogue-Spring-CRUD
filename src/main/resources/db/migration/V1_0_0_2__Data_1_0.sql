/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  DyoRizqal
 * Created: Nov 27, 2015
 */
INSERT INTO blogue (id,tanggal,judul,isi,author) VALUES
('1','8 Agustus 2016','Ini Judul','Ini Isi','Ini Author'),
('2','9 Agustus 2016','Ini Judul','Ini Isi','Ini Author');

INSERT INTO `c_security_role` (`id`,`nama`,`description`) VALUES
('admin','admin','Application Admin'),
('user','user','Application User');

INSERT INTO `c_security_permission` (`id`,`permission_label`,`permission_value`) VALUES
('blogue_update','Edit Blogue','ROLE_BLOGUE_UPDATE'),
('blogue_view','View Blogue','ROLE_BLOGUE_VIEW'),
('blogue_create','Create Blogue','ROLE_BLOGUE_CREATE'),
('blogue_delete','Delete Blogue','ROLE_BLOGUE_DELETE'),
('user_view','View User','ROLE_USER_VIEW');

INSERT INTO `c_security_role_permission` (`id_role`,`id_permission`) VALUES
('admin','blogue_update'),
('admin','blogue_view'),
('admin','blogue_create'),
('admin','blogue_delete'),
('user','blogue_view'),
('user','user_view'),
('admin','user_view');

INSERT INTO `c_security_user` (`id`,`username`,`password`,`active`,`id_role`) VALUES
('1','admin','admin',true,'admin'),
('2','user','user',true,'user');
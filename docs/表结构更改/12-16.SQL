ALTER TABLE `ourway_sys_page_component`
  ADD COLUMN `KJ_BIND_KEY` VARCHAR(64) NULL ;

SELECT KJ_ATTRIBUTE,KJ_BIND_KEY,KJ_TYPE FROM `ourway_sys_page_component`
-- 修改控件绑定属性，统一修改
UPDATE ourway_sys_page_component SET KJ_BIND_KEY=KJ_ATTRIBUTE WHERE kj_type!=2

ALTER TABLE `owboss`.`ourway_sys_privsallocate`
  CHANGE `ROLE_REF_OW_ID` `ROLE_REF_OWID` VARCHAR(64) CHARSET utf8 COLLATE utf8_general_ci NULL;

ALTER TABLE `owboss`.`ourway_sys_privsallocate`
  CHANGE `MENU_REF_ID` `MENU_REF_OWID` INT(11) NULL;

ALTER TABLE `owboss`.`ourway_sys_dfilter`
  CHANGE `USER_REF_OW_ID` `USER_REF_OWID` VARCHAR(64) CHARSET utf8 COLLATE utf8_general_ci NULL;

ALTER TABLE `owboss`.`ourway_sys_page_component`
  CHANGE `KJ_FORMAT` `KJ_FORMAT` VARCHAR(1600) CHARSET utf8 COLLATE utf8_general_ci NULL;


ALTER TABLE `ourway_sys_flow_class`
  ADD COLUMN `CONDITIONS` VARCHAR(640) NULL AFTER `VARS`;

ALTER TABLE `ourway_sys_page`
  ADD COLUMN `PAGE_INIT_AFTER` VARCHAR(240) NULL AFTER `STATE`;

ALTER TABLE `ourway_sys_layout`
  ADD COLUMN `CONTROL_WAY` TINYINT(4) NULL AFTER `CONTROL_BANDBOX_INT`;

  ALTER TABLE `ourway_sys_object_attribute`
  ADD COLUMN `ATTRIBUTE_DISPLAYCLASS` VARCHAR(240) NULL AFTER `ATTRIBUTE_DISPLAY`;

  ALTER TABLE `ourway_sys_employs`
   CHANGE `EMP_TYPE` `EMP_TYPE` VARCHAR(32) NULL


alter table ourway_sys_language
   change `LABEL_TYPE` `LABEL_TYPE` varchar(64) NULL;
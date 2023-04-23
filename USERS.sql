/*
 Navicat Premium Data Transfer

 Source Server         : ORACLE
 Source Server Type    : Oracle
 Source Server Version : 190000
 Source Schema         : USERS

 Target Server Type    : Oracle
 Target Server Version : 190000
 File Encoding         : 65001

 Date: 23/04/2023 13:54:25
*/


-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE "USERS"."users";
CREATE TABLE "USERS"."users" (
  "id" NUMBER VISIBLE DEFAULT "USERS"."ISEQ$$_73064".nextval NOT NULL,
  "username" VARCHAR2(50 BYTE) VISIBLE,
  "password" VARCHAR2(255 BYTE) VISIBLE,
  "rol" NUMBER VISIBLE
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO "USERS"."users" VALUES ('25', 'administrador', '$2a$10$8l5ZiiF8KebIbCQQ5f0O7.4NHk660iGg897A.ivMFAqGkAGa3wHji', '1');
INSERT INTO "USERS"."users" VALUES ('21', 'contador', '$2a$10$tJcd8jMgCEYcG2rP3vWHF.mefOGfVNsQfuWKeDrwqv0GTyzU6k9lW', '2');

-- ----------------------------
-- Sequence structure for ISEQ$$_73064
-- ----------------------------
DROP SEQUENCE "USERS"."ISEQ$$_73064";
CREATE SEQUENCE "USERS"."ISEQ$$_73064" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 20;

-- ----------------------------
-- Primary Key structure for table users
-- ----------------------------
ALTER TABLE "USERS"."users" ADD CONSTRAINT "SYS_C007422" PRIMARY KEY ("id");

-- ----------------------------
-- Checks structure for table users
-- ----------------------------
ALTER TABLE "USERS"."users" ADD CONSTRAINT "SYS_C007421" CHECK ("id" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

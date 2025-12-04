-- 회원 테이블 생성 및 기존 테이블 수정
-- 실행일: 2025-12-04

-- 1. membership_tb 테이블 생성
CREATE TABLE IF NOT EXISTS `membership_tb` (
  `m_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '회원 ID',
  `m_user` bigint(20) NOT NULL DEFAULT 0 COMMENT '사용자 ID (user_tb 참조)',
  `m_gym` bigint(20) NOT NULL DEFAULT 0 COMMENT '헬스장 ID (gym_tb 참조)',
  `m_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  PRIMARY KEY (`m_id`),
  INDEX idx_user (m_user),
  INDEX idx_gym (m_gym)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='회원 테이블';

-- 2. usehealth_tb 테이블에 uh_membership 컬럼 추가
ALTER TABLE `usehealth_tb` 
ADD COLUMN `uh_membership` bigint(20) NOT NULL DEFAULT 0 COMMENT '회원 ID (membership_tb 참조)' AFTER `uh_health`,
ADD INDEX idx_membership (uh_membership);

-- 3. usehealth_tb의 uh_user 컬럼 코멘트 수정 (DEPRECATED 표시)
ALTER TABLE `usehealth_tb` 
MODIFY COLUMN `uh_user` bigint(20) NOT NULL DEFAULT 0 COMMENT '사용자 ID (user_tb 참조) - DEPRECATED: uh_membership 사용 권장';

-- 4. usehealthusage_tb 테이블에 uhu_membership 컬럼 추가
ALTER TABLE `usehealthusage_tb` 
ADD COLUMN `uhu_membership` bigint(20) NOT NULL DEFAULT 0 COMMENT '회원 ID (membership_tb 참조)' AFTER `uhu_usehealth`,
ADD INDEX idx_membership (uhu_membership);

-- 5. usehealthusage_tb의 uhu_user 컬럼 코멘트 수정 (DEPRECATED 표시)
ALTER TABLE `usehealthusage_tb` 
MODIFY COLUMN `uhu_user` bigint(20) NOT NULL DEFAULT 0 COMMENT '사용자 ID (user_tb 참조) - DEPRECATED: uhu_membership 사용 권장';

-- 변경사항 확인
SELECT 'membership_tb 테이블 생성 완료' as status;
SELECT 'usehealth_tb에 uh_membership 컬럼 추가 완료' as status;
SELECT 'usehealthusage_tb에 uhu_membership 컬럼 추가 완료' as status;

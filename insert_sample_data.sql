-- ============================================
-- 기존 데이터 삭제 및 샘플 데이터 삽입
-- ============================================

-- 외래키 체크 비활성화
SET FOREIGN_KEY_CHECKS = 0;

-- 기존 데이터 삭제
TRUNCATE TABLE membershipusage_tb;
TRUNCATE TABLE workoutlog_tb;
TRUNCATE TABLE appversion_tb;
TRUNCATE TABLE pushtoken_tb;
TRUNCATE TABLE rockerusage_tb;
TRUNCATE TABLE inquiry_tb;
TRUNCATE TABLE memberbody_tb;
TRUNCATE TABLE ptreservation_tb;
TRUNCATE TABLE trainermember_tb;
TRUNCATE TABLE notice_tb;
TRUNCATE TABLE memberqr_tb;
TRUNCATE TABLE attendance_tb;
TRUNCATE TABLE token_tb;
TRUNCATE TABLE stop_tb;
TRUNCATE TABLE systemlog_tb;
TRUNCATE TABLE setting_tb;
TRUNCATE TABLE loginlog_tb;
TRUNCATE TABLE ipblock_tb;
TRUNCATE TABLE alarm_tb;
TRUNCATE TABLE role_tb;
TRUNCATE TABLE usehealth_tb;
TRUNCATE TABLE paymentform_tb;
TRUNCATE TABLE paymenttype_tb;
TRUNCATE TABLE payment_tb;
TRUNCATE TABLE order_tb;
TRUNCATE TABLE membership_tb;
TRUNCATE TABLE rocker_tb;
TRUNCATE TABLE rockergroup_tb;
TRUNCATE TABLE health_tb;
TRUNCATE TABLE healthcategory_tb;
TRUNCATE TABLE term_tb;
TRUNCATE TABLE discount_tb;
TRUNCATE TABLE daytype_tb;
TRUNCATE TABLE user_tb;
TRUNCATE TABLE gym_tb;

-- 외래키 체크 활성화
SET FOREIGN_KEY_CHECKS = 1;

-- 1. gym_tb (헬스장 테이블)
INSERT INTO gym_tb (g_name, g_date) VALUES
('서울 강남점', NOW()),
('서울 홍대점', NOW()),
('서울 신촌점', NOW()),
('부산 해운대점', NOW()),
('대구 동성로점', NOW()),
('인천 구월점', NOW()),
('광주 충장로점', NOW()),
('대전 둔산점', NOW()),
('울산 삼산점', NOW()),
('수원 영통점', NOW());

-- 2. user_tb (사용자 테이블) - 비밀번호 모두 0000
INSERT INTO user_tb (u_loginid, u_passwd, u_email, u_name, u_tel, u_address, u_sex, u_birth, u_type, u_level, u_role, u_use, u_date) VALUES
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'admin@gym.com', '관리자', '010-1111-1111', '서울시 강남구', 0, '1980-01-01', 0, 9, 4, 0, NOW()),
('gym_admin1', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'gym1@gym.com', '강남점관리자', '010-2222-2222', '서울시 강남구', 0, '1985-03-15', 0, 3, 3, 0, NOW()),
('trainer1', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'trainer1@gym.com', '김트레이너', '010-3333-3333', '서울시 강남구', 0, '1990-05-20', 0, 2, 1, 0, NOW()),
('trainer2', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'trainer2@gym.com', '이트레이너', '010-4444-4444', '서울시 강남구', 1, '1992-07-10', 0, 2, 1, 0, NOW()),
('member1', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'member1@gym.com', '박회원', '010-5555-5555', '서울시 강남구', 0, '1995-01-25', 0, 1, 0, 0, NOW()),
('member2', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'member2@gym.com', '최회원', '010-6666-6666', '서울시 서초구', 1, '1997-03-30', 0, 1, 0, 0, NOW()),
('member3', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'member3@gym.com', '정회원', '010-7777-7777', '서울시 송파구', 0, '1993-09-12', 0, 1, 0, 0, NOW()),
('member4', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'member4@gym.com', '강회원', '010-8888-8888', '서울시 마포구', 1, '1998-11-05', 0, 1, 0, 0, NOW()),
('staff1', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'staff1@gym.com', '유직원', '010-9999-9999', '서울시 강남구', 1, '1994-06-18', 0, 2, 2, 0, NOW()),
('member5', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'member5@gym.com', '윤회원', '010-1010-1010', '서울시 용산구', 0, '1996-08-22', 0, 1, 0, 0, NOW());

-- 3. daytype_tb (요일 타입 테이블)
INSERT INTO daytype_tb (dt_gym, dt_name, dt_date) VALUES
(1, '평일', NOW()),
(1, '주말', NOW()),
(1, '공휴일', NOW()),
(2, '평일', NOW()),
(2, '주말', NOW()),
(3, '평일', NOW()),
(3, '주말', NOW()),
(4, '평일', NOW()),
(5, '평일', NOW()),
(6, '평일', NOW());

-- 4. discount_tb (할인 테이블)
INSERT INTO discount_tb (d_name, d_discount, d_date) VALUES
('신규회원', 20, NOW()),
('학생할인', 15, NOW()),
('군인할인', 25, NOW()),
('가족할인', 10, NOW()),
('직장인할인', 12, NOW()),
('시니어할인', 30, NOW()),
('조기등록', 18, NOW()),
('단체할인', 22, NOW()),
('재등록할인', 8, NOW()),
('VIP할인', 35, NOW());

-- 5. term_tb (기간 테이블)
INSERT INTO term_tb (t_gym, t_daytype, t_name, t_term, t_date) VALUES
(1, 1, '1개월', 30, NOW()),
(1, 1, '3개월', 90, NOW()),
(1, 1, '6개월', 180, NOW()),
(1, 1, '12개월', 365, NOW()),
(2, 4, '1개월', 30, NOW()),
(2, 4, '3개월', 90, NOW()),
(3, 6, '1개월', 30, NOW()),
(3, 6, '6개월', 180, NOW()),
(4, 8, '3개월', 90, NOW()),
(5, 9, '12개월', 365, NOW());

-- 6. healthcategory_tb (운동권 카테고리 테이블)
INSERT INTO healthcategory_tb (hc_gym, hc_name, hc_date) VALUES
(1, '헬스', NOW()),
(1, 'PT', NOW()),
(1, '요가', NOW()),
(2, '헬스', NOW()),
(2, 'PT', NOW()),
(3, '헬스', NOW()),
(3, '필라테스', NOW()),
(4, '헬스', NOW()),
(5, 'PT', NOW()),
(6, '크로스핏', NOW());

-- 7. health_tb (운동권 테이블)
INSERT INTO health_tb (h_category, h_term, h_name, h_count, h_cost, h_discount, h_costdiscount, h_content, h_date) VALUES
(1, 1, '헬스 1개월', 0, 100000, 1, 80000, '1개월 헬스 이용권', NOW()),
(1, 2, '헬스 3개월', 0, 270000, 1, 230000, '3개월 헬스 이용권', NOW()),
(2, 1, 'PT 10회', 10, 500000, 2, 425000, 'PT 10회 이용권', NOW()),
(2, 1, 'PT 20회', 20, 950000, 2, 807500, 'PT 20회 이용권', NOW()),
(3, 1, '요가 1개월', 0, 120000, 3, 90000, '1개월 요가 이용권', NOW()),
(4, 5, '헬스 1개월', 0, 95000, 4, 85500, '1개월 헬스 이용권', NOW()),
(5, 6, 'PT 10회', 10, 480000, 5, 422400, 'PT 10회 이용권', NOW()),
(6, 7, '헬스 1개월', 0, 105000, 1, 84000, '1개월 헬스 이용권', NOW()),
(7, 8, '필라테스 3개월', 0, 330000, 6, 231000, '3개월 필라테스 이용권', NOW()),
(8, 9, '헬스 3개월', 0, 260000, 7, 213200, '3개월 헬스 이용권', NOW());

-- 8. rockergroup_tb (락커 그룹 테이블)
INSERT INTO rockergroup_tb (rg_gym, rg_name, rg_date) VALUES
(1, '남자 락커실', NOW()),
(1, '여자 락커실', NOW()),
(2, '남자 락커실', NOW()),
(2, '여자 락커실', NOW()),
(3, '남자 락커실', NOW()),
(4, '남자 락커실', NOW()),
(4, '여자 락커실', NOW()),
(5, '남자 락커실', NOW()),
(6, '남자 락커실', NOW()),
(7, '남자 락커실', NOW());

-- 9. rocker_tb (락커 테이블)
INSERT INTO rocker_tb (r_group, r_name, r_available, r_date) VALUES
(1, '101', 1, NOW()),
(1, '102', 1, NOW()),
(1, '103', 0, NOW()),
(2, '201', 1, NOW()),
(2, '202', 1, NOW()),
(3, '101', 1, NOW()),
(3, '102', 0, NOW()),
(4, '201', 1, NOW()),
(5, '101', 1, NOW()),
(6, '101', 1, NOW());

-- 10. membership_tb (회원 테이블)
INSERT INTO membership_tb (m_gym, m_user, m_name, m_sex, m_birth, m_phonenum, m_address, m_image, m_date) VALUES
(1, 5, '박회원', 0, '1995-01-25', '010-5555-5555', '서울시 강남구', '', NOW()),
(1, 6, '최회원', 1, '1997-03-30', '010-6666-6666', '서울시 서초구', '', NOW()),
(1, 7, '정회원', 0, '1993-09-12', '010-7777-7777', '서울시 송파구', '', NOW()),
(2, 8, '강회원', 1, '1998-11-05', '010-8888-8888', '서울시 마포구', '', NOW()),
(2, 10, '윤회원', 0, '1996-08-22', '010-1010-1010', '서울시 용산구', '', NOW()),
(3, 5, '박회원', 0, '1995-01-25', '010-5555-5555', '서울시 강남구', '', NOW()),
(4, 6, '최회원', 1, '1997-03-30', '010-6666-6666', '서울시 서초구', '', NOW()),
(5, 7, '정회원', 0, '1993-09-12', '010-7777-7777', '서울시 송파구', '', NOW()),
(6, 8, '강회원', 1, '1998-11-05', '010-8888-8888', '서울시 마포구', '', NOW()),
(7, 10, '윤회원', 0, '1996-08-22', '010-1010-1010', '서울시 용산구', '', NOW());

-- 11. order_tb (주문 테이블)
INSERT INTO order_tb (o_membership, o_date) VALUES
(1, NOW()),
(2, NOW()),
(3, NOW()),
(4, NOW()),
(5, NOW()),
(6, NOW()),
(7, NOW()),
(8, NOW()),
(9, NOW()),
(10, NOW());

-- 12. payment_tb (결제 테이블)
INSERT INTO payment_tb (p_gym, p_order, p_membership, p_cost, p_date) VALUES
(1, 1, 1, 80000, NOW()),
(1, 2, 2, 230000, NOW()),
(1, 3, 3, 425000, NOW()),
(2, 4, 4, 90000, NOW()),
(2, 5, 5, 85500, NOW()),
(3, 6, 6, 422400, NOW()),
(4, 7, 7, 84000, NOW()),
(5, 8, 8, 231000, NOW()),
(6, 9, 9, 213200, NOW()),
(7, 10, 10, 80000, NOW());

-- 13. paymenttype_tb (결제 타입 테이블)
INSERT INTO paymenttype_tb (pt_gym, pt_name, pt_date) VALUES
(1, '현금', NOW()),
(1, '카드', NOW()),
(1, '계좌이체', NOW()),
(2, '현금', NOW()),
(2, '카드', NOW()),
(3, '현금', NOW()),
(3, '카드', NOW()),
(4, '카드', NOW()),
(5, '현금', NOW()),
(6, '카드', NOW());

-- 14. paymentform_tb (결제 상세 테이블)
INSERT INTO paymentform_tb (pf_gym, pf_payment, pf_type, pf_cost, pf_date) VALUES
(1, 1, 2, 80000, NOW()),
(1, 2, 2, 230000, NOW()),
(1, 3, 2, 425000, NOW()),
(2, 4, 5, 90000, NOW()),
(2, 5, 5, 85500, NOW()),
(3, 6, 7, 422400, NOW()),
(4, 7, 8, 84000, NOW()),
(5, 8, 9, 231000, NOW()),
(6, 9, 10, 213200, NOW()),
(7, 10, 10, 80000, NOW());

-- 15. usehealth_tb (운동권 사용 테이블)
INSERT INTO usehealth_tb (uh_order, uh_health, uh_user, uh_rocker, uh_term, uh_discount, uh_startday, uh_endday, uh_date) VALUES
(1, 1, 5, 0, 1, 1, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), NOW()),
(2, 2, 6, 0, 2, 1, NOW(), DATE_ADD(NOW(), INTERVAL 90 DAY), NOW()),
(3, 3, 7, 0, 1, 2, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), NOW()),
(4, 5, 8, 0, 1, 3, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), NOW()),
(5, 6, 10, 0, 5, 4, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), NOW()),
(6, 7, 5, 0, 6, 5, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), NOW()),
(7, 8, 6, 0, 7, 1, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), NOW()),
(8, 9, 7, 0, 8, 6, NOW(), DATE_ADD(NOW(), INTERVAL 90 DAY), NOW()),
(9, 10, 8, 0, 9, 7, NOW(), DATE_ADD(NOW(), INTERVAL 90 DAY), NOW()),
(10, 1, 10, 0, 1, 1, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), NOW());

-- 16. role_tb (역할 테이블)
INSERT INTO role_tb (r_gym, r_role, r_name, r_date) VALUES
(1, 0, '회원', NOW()),
(1, 1, '트레이너', NOW()),
(1, 2, '직원', NOW()),
(2, 0, '회원', NOW()),
(2, 1, '트레이너', NOW()),
(3, 0, '회원', NOW()),
(4, 0, '회원', NOW()),
(5, 0, '회원', NOW()),
(6, 1, '트레이너', NOW()),
(7, 0, '회원', NOW());

-- 17. alarm_tb (알람 테이블)
INSERT INTO alarm_tb (al_title, al_content, al_type, al_status, al_user, al_date) VALUES
('회원권 만료 예정', '회원권이 7일 후 만료됩니다.', 0, 0, 5, NOW()),
('PT 예약 확정', 'PT 수업이 확정되었습니다.', 0, 0, 6, NOW()),
('락커 만료 예정', '락커 이용기간이 3일 남았습니다.', 1, 0, 7, NOW()),
('결제 완료', '결제가 정상적으로 완료되었습니다.', 3, 0, 8, NOW()),
('신규 공지사항', '새로운 공지사항이 등록되었습니다.', 0, 0, 5, NOW()),
('회원권 연장', '회원권이 연장되었습니다.', 3, 0, 6, NOW()),
('PT 취소', 'PT 수업이 취소되었습니다.', 1, 1, 7, NOW()),
('이벤트 알림', '신규 이벤트가 시작되었습니다.', 0, 0, 8, NOW()),
('시스템 점검', '시스템 점검 예정입니다.', 1, 0, 10, NOW()),
('회원권 구매', '회원권 구매가 완료되었습니다.', 0, 0, 5, NOW());

-- 18. ipblock_tb (IP 차단 테이블)
INSERT INTO ipblock_tb (ib_address, ib_type, ib_policy, ib_use, ib_order, ib_date) VALUES
('192.168.0.1', 0, 0, 0, 1, NOW()),
('10.0.0.1', 0, 0, 0, 2, NOW()),
('172.16.0.1', 1, 1, 0, 3, NOW()),
('192.168.1.1', 0, 0, 0, 4, NOW()),
('10.10.10.1', 0, 0, 1, 5, NOW()),
('8.8.8.8', 0, 0, 0, 6, NOW()),
('1.1.1.1', 0, 0, 0, 7, NOW()),
('192.168.100.1', 1, 0, 0, 8, NOW()),
('172.20.0.1', 0, 0, 0, 9, NOW()),
('10.1.1.1', 0, 0, 0, 10, NOW());

-- 19. loginlog_tb (로그인 로그 테이블)
INSERT INTO loginlog_tb (ll_ip, ll_ipvalue, ll_user, ll_date) VALUES
('192.168.0.100', 3232235620, 1, NOW()),
('192.168.0.101', 3232235621, 2, NOW()),
('192.168.0.102', 3232235622, 3, NOW()),
('192.168.0.103', 3232235623, 4, NOW()),
('192.168.0.104', 3232235624, 5, NOW()),
('192.168.0.105', 3232235625, 6, NOW()),
('192.168.0.106', 3232235626, 7, NOW()),
('192.168.0.107', 3232235627, 8, NOW()),
('192.168.0.108', 3232235628, 9, NOW()),
('192.168.0.109', 3232235629, 10, NOW());

-- 20. setting_tb (설정 테이블)
INSERT INTO setting_tb (se_category, se_name, se_key, se_value, se_remark, se_type, se_data, se_order, se_date) VALUES
('시스템', '사이트명', 'site_name', '헬스장 관리 시스템', '사이트 이름', 0, '{}', 1, NOW()),
('시스템', '관리자 이메일', 'admin_email', 'admin@gym.com', '관리자 이메일', 0, '{}', 2, NOW()),
('결제', '최소 결제금액', 'min_payment', '10000', '최소 결제 금액', 1, '{}', 3, NOW()),
('회원권', '기본 유효기간', 'default_term', '30', '기본 유효 기간(일)', 1, '{}', 4, NOW()),
('알림', '만료 알림일', 'expiry_notice_days', '7', '만료 며칠 전 알림', 1, '{}', 5, NOW()),
('시스템', '유지보수 모드', 'maintenance_mode', 'false', '유지보수 모드', 2, '{}', 6, NOW()),
('회원', '자동 승인', 'auto_approve', 'true', '회원 자동 승인', 2, '{}', 7, NOW()),
('락커', '기본 보증금', 'locker_deposit', '50000', '락커 기본 보증금', 1, '{}', 8, NOW()),
('PT', 'PT 1회당 시간', 'pt_duration', '60', 'PT 1회 시간(분)', 1, '{}', 9, NOW()),
('시스템', '로고 URL', 'logo_url', '/images/logo.png', '로고 이미지 URL', 0, '{}', 10, NOW());

-- 21. systemlog_tb (시스템 로그 테이블)
INSERT INTO systemlog_tb (sl_type, sl_content, sl_result, sl_date) VALUES
(0, '관리자 로그인', 0, NOW()),
(0, '회원 로그인 시도', 1, NOW()),
(0, '트레이너 로그인', 0, NOW()),
(1, '데이터 동기화', 0, NOW()),
(0, '관리자 로그아웃', 0, NOW()),
(1, '스케줄러 실행', 0, NOW()),
(0, '회원 로그인', 0, NOW()),
(1, '백업 실행', 0, NOW()),
(0, '직원 로그인', 0, NOW()),
(1, '알림 발송', 0, NOW());

-- 22. stop_tb (일시정지 테이블)
INSERT INTO stop_tb (s_usehelth, s_startday, s_endday, s_count, s_date) VALUES
(1, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 7, NOW()),
(2, NOW(), DATE_ADD(NOW(), INTERVAL 10 DAY), 10, NOW()),
(3, NOW(), DATE_ADD(NOW(), INTERVAL 5 DAY), 5, NOW()),
(4, NOW(), DATE_ADD(NOW(), INTERVAL 14 DAY), 14, NOW()),
(5, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 7, NOW()),
(6, NOW(), DATE_ADD(NOW(), INTERVAL 3 DAY), 3, NOW()),
(7, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 7, NOW()),
(8, NOW(), DATE_ADD(NOW(), INTERVAL 10 DAY), 10, NOW()),
(9, NOW(), DATE_ADD(NOW(), INTERVAL 5 DAY), 5, NOW()),
(10, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 7, NOW());

-- 23. token_tb (토큰 테이블)
INSERT INTO token_tb (to_user, to_token, to_status, to_date) VALUES
(1, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.admin', 0, NOW()),
(2, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.gym1', 0, NOW()),
(3, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.trainer1', 0, NOW()),
(4, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.trainer2', 0, NOW()),
(5, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.member1', 0, NOW()),
(6, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.member2', 0, NOW()),
(7, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.member3', 0, NOW()),
(8, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.member4', 0, NOW()),
(9, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.staff1', 0, NOW()),
(10, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.member5', 0, NOW());

-- 24. attendance_tb (출석 테이블)
INSERT INTO attendance_tb (at_user, at_gym, at_membership, at_type, at_method, at_checkintime, at_checkouttime, at_status, at_note, at_date) VALUES
(5, 1, 1, 0, 0, NOW(), DATE_ADD(NOW(), INTERVAL 2 HOUR), 0, 0, NOW()),
(6, 1, 2, 0, 0, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), 0, 0, NOW()),
(7, 1, 3, 1, 1, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), 0, 'PT 수업', NOW()),
(8, 2, 4, 2, 0, NOW(), DATE_ADD(NOW(), INTERVAL 1.5 HOUR), 0, '요가 수업', NOW()),
(10, 2, 5, 0, 2, NOW(), DATE_ADD(NOW(), INTERVAL 2 HOUR), 0, 0, NOW()),
(5, 3, 6, 0, 0, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), 0, 0, NOW()),
(6, 4, 7, 1, 1, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), 0, 'PT 수업', NOW()),
(7, 5, 8, 2, 0, NOW(), DATE_ADD(NOW(), INTERVAL 1.5 HOUR), 0, '필라테스', NOW()),
(8, 6, 9, 0, 0, NOW(), DATE_ADD(NOW(), INTERVAL 2 HOUR), 0, 0, NOW()),
(10, 7, 10, 0, 2, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), 0, 0, NOW());

-- 25. memberqr_tb (회원 QR 코드 테이블)
INSERT INTO memberqr_tb (mq_user, mq_code, mq_imageurl, mq_isactive, mq_expiredate, mq_generateddate, mq_lastuseddate, mq_usecount, mq_date) VALUES
(5, UUID(), 0, 1, DATE_ADD(NOW(), INTERVAL 1 YEAR), NOW(), NOW(), 15, NOW()),
(6, UUID(), 0, 1, DATE_ADD(NOW(), INTERVAL 1 YEAR), NOW(), NOW(), 22, NOW()),
(7, UUID(), 0, 1, DATE_ADD(NOW(), INTERVAL 1 YEAR), NOW(), NOW(), 8, NOW()),
(8, UUID(), 0, 1, DATE_ADD(NOW(), INTERVAL 1 YEAR), NOW(), NOW(), 30, NOW()),
(10, UUID(), 0, 1, DATE_ADD(NOW(), INTERVAL 1 YEAR), NOW(), NOW(), 12, NOW()),
(1, UUID(), 0, 0, DATE_ADD(NOW(), INTERVAL 1 YEAR), NOW(), 0, 0, NOW()),
(2, UUID(), 0, 0, DATE_ADD(NOW(), INTERVAL 1 YEAR), NOW(), 0, 0, NOW()),
(3, UUID(), 0, 0, DATE_ADD(NOW(), INTERVAL 1 YEAR), NOW(), 0, 0, NOW()),
(4, UUID(), 0, 0, DATE_ADD(NOW(), INTERVAL 1 YEAR), NOW(), 0, 0, NOW()),
(9, UUID(), 0, 0, DATE_ADD(NOW(), INTERVAL 1 YEAR), NOW(), 0, 0, NOW());

-- 26. notice_tb (공지사항 테이블)
INSERT INTO notice_tb (nt_gym, nt_type, nt_title, nt_content, nt_target, nt_viewcount, nt_startdate, nt_enddate, nt_status, nt_createdby, nt_createddate, nt_date) VALUES
(1, 0, '신규 오픈 이벤트', '헬스장 오픈 기념 첫 달 회원권 50% 할인!', 0, 125, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 1, 2, NOW(), NOW()),
(1, 1, '운영 시간 안내', '평일: 06:00-23:00, 주말: 08:00-20:00', 0, 89, NOW(), DATE_ADD(NOW(), INTERVAL 90 DAY), 1, 2, NOW(), NOW()),
(1, 2, '임시 휴무 안내', '설 연휴 임시 휴무 안내', 0, 45, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 1, 2, NOW(), NOW()),
(2, 0, 'PT 할인 이벤트', 'PT 10회 등록시 2회 추가 증정', 1, 67, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 1, 2, NOW(), NOW()),
(2, 1, '시설 점검 안내', '에어컨 점검으로 인한 일부 시설 이용 제한', 0, 34, NOW(), DATE_ADD(NOW(), INTERVAL 3 DAY), 1, 2, NOW(), NOW()),
(3, 0, '신규 프로그램 안내', '필라테스 클래스 신규 오픈', 0, 56, NOW(), DATE_ADD(NOW(), INTERVAL 60 DAY), 1, 2, NOW(), NOW()),
(4, 2, '회원권 환불 정책', '회원권 환불 정책 변경 안내', 0, 23, NOW(), DATE_ADD(NOW(), INTERVAL 90 DAY), 1, 2, NOW(), NOW()),
(5, 0, '여름 특별 이벤트', '6월 한정 여름 특별 할인', 0, 78, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 1, 2, NOW(), NOW()),
(6, 1, '락커 이용 안내', '락커 이용 규정 및 보증금 안내', 0, 41, NOW(), DATE_ADD(NOW(), INTERVAL 180 DAY), 1, 2, NOW(), NOW()),
(7, 0, '추천 이벤트', '친구 추천시 할인 혜택', 0, 92, NOW(), DATE_ADD(NOW(), INTERVAL 60 DAY), 1, 2, NOW(), NOW());

-- 27. trainermember_tb (트레이너-회원 매칭 테이블)
INSERT INTO trainermember_tb (tm_trainer, tm_member, tm_gym, tm_status, tm_startdate, tm_enddate, tm_note, tm_date) VALUES
(3, 5, 1, 1, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 'PT 회원', NOW()),
(3, 6, 1, 1, NOW(), DATE_ADD(NOW(), INTERVAL 90 DAY), 'PT 3개월 등록', NOW()),
(4, 7, 1, 1, NOW(), DATE_ADD(NOW(), INTERVAL 60 DAY), 'PT 회원', NOW()),
(3, 8, 2, 0, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), '만료 예정', NOW()),
(4, 10, 2, 1, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 'PT 신규', NOW()),
(3, 5, 1, 1, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 'PT 재등록', NOW()),
(4, 6, 1, 1, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 'PT 회원', NOW()),
(3, 7, 1, 1, NOW(), DATE_ADD(NOW(), INTERVAL 90 DAY), 'PT 장기 회원', NOW()),
(4, 8, 2, 1, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 'PT 회원', NOW()),
(3, 10, 2, 1, NOW(), DATE_ADD(NOW(), INTERVAL 60 DAY), 'PT 2개월', NOW());

-- 28. ptreservation_tb (PT 예약 테이블)
INSERT INTO ptreservation_tb (pr_trainer, pr_member, pr_gym, pr_reservationdate, pr_starttime, pr_endtime, pr_duration, pr_status, pr_note, pr_cancelreason, pr_createddate, pr_date) VALUES
(3, 5, 1, DATE_ADD(NOW(), INTERVAL 1 DAY), '10:00:00', '11:00:00', 60, 0, '', '', NOW(), NOW()),
(3, 6, 1, DATE_ADD(NOW(), INTERVAL 1 DAY), '11:00:00', '12:00:00', 60, 0, '', '', NOW(), NOW()),
(4, 7, 1, DATE_ADD(NOW(), INTERVAL 2 DAY), '14:00:00', '15:00:00', 60, 0, '하체 운동', '', NOW(), NOW()),
(3, 8, 2, DATE_ADD(NOW(), INTERVAL 1 DAY), '16:00:00', '17:00:00', 60, 0, '', '', NOW(), NOW()),
(4, 10, 2, DATE_ADD(NOW(), INTERVAL 3 DAY), '09:00:00', '10:00:00', 60, 0, '상체 운동', '', NOW(), NOW()),
(3, 5, 1, DATE_ADD(NOW(), INTERVAL 5 DAY), '10:00:00', '11:00:00', 60, 0, '', '', NOW(), NOW()),
(4, 6, 1, DATE_ADD(NOW(), INTERVAL 4 DAY), '15:00:00', '16:00:00', 60, 0, '전신 운동', '', NOW(), NOW()),
(3, 7, 1, DATE_ADD(NOW(), INTERVAL 6 DAY), '11:00:00', '12:00:00', 60, 0, '', '', NOW(), NOW()),
(4, 8, 2, DATE_ADD(NOW(), INTERVAL 2 DAY), '17:00:00', '18:00:00', 60, 2, '취소됨', '일정 변경', NOW(), NOW()),
(3, 10, 2, DATE_ADD(NOW(), INTERVAL 7 DAY), '08:00:00', '09:00:00', 60, 0, '아침 운동', '', NOW(), NOW());

-- 29. memberbody_tb (회원 신체 정보 테이블)
INSERT INTO memberbody_tb (mb_user, mb_weight, mb_height, mb_bodyfat, mb_musclemass, mb_bmi, mb_note, mb_measuredby, mb_measureddate, mb_date) VALUES
(5, 75.5, 175.0, 18.5, 58.2, 24.6, '초기 측정', 3, NOW(), NOW()),
(6, 58.3, 162.0, 22.3, 42.1, 22.2, '초기 측정', 3, NOW(), NOW()),
(7, 82.1, 178.0, 20.1, 63.5, 25.9, '초기 측정', 4, NOW(), NOW()),
(8, 54.2, 158.0, 24.5, 38.7, 21.7, '초기 측정', 3, NOW(), NOW()),
(10, 70.8, 172.0, 17.2, 55.9, 23.9, '초기 측정', 4, NOW(), NOW()),
(5, 73.2, 175.0, 17.1, 59.5, 23.9, '1개월 후', 3, DATE_ADD(NOW(), INTERVAL 30 DAY), NOW()),
(6, 56.8, 162.0, 20.8, 43.2, 21.6, '1개월 후', 3, DATE_ADD(NOW(), INTERVAL 30 DAY), NOW()),
(7, 80.5, 178.0, 18.9, 64.8, 25.4, '1개월 후', 4, DATE_ADD(NOW(), INTERVAL 30 DAY), NOW()),
(8, 53.1, 158.0, 23.2, 39.5, 21.3, '1개월 후', 3, DATE_ADD(NOW(), INTERVAL 30 DAY), NOW()),
(10, 69.3, 172.0, 16.5, 56.8, 23.4, '1개월 후', 4, DATE_ADD(NOW(), INTERVAL 30 DAY), NOW());

-- 30. inquiry_tb (문의 테이블)
INSERT INTO inquiry_tb (iq_user, iq_gym, iq_type, iq_title, iq_content, iq_status, iq_answer, iq_answeredby, iq_answereddate, iq_createddate, iq_date) VALUES
(5, 1, 0, '운영 시간 문의', '주말 운영 시간이 궁금합니다.', 1, '주말은 08:00-20:00 운영합니다.', 2, NOW(), NOW(), NOW()),
(6, 1, 1, '회원권 환불 문의', '회원권 환불 가능한가요?', 1, '환불 규정에 따라 가능합니다.', 2, NOW(), NOW(), NOW()),
(7, 1, 2, '락커 이용 문의', '락커 월 이용료는 얼마인가요?', 1, '월 3만원입니다.', 2, NOW(), NOW(), NOW()),
(8, 2, 0, 'PT 가격 문의', 'PT 10회 가격이 궁금합니다.', 1, '48만원이며 할인 이벤트 중입니다.', 2, NOW(), NOW(), NOW()),
(10, 2, 3, '시설 문의', '사우나 시설이 있나요?', 1, '네, 남/여 사우나 모두 있습니다.', 2, NOW(), NOW(), NOW()),
(5, 3, 0, '주차 문의', '주차 가능한가요?', 0, '', 0, NOW(), NOW(), NOW()),
(6, 4, 1, '회원권 연장 문의', '회원권 연장 방법이 궁금합니다.', 0, '', 0, NOW(), NOW(), NOW()),
(7, 5, 4, '기타 문의', '단체 할인이 있나요?', 1, '5인 이상 단체 등록시 할인됩니다.', 2, NOW(), NOW(), NOW()),
(8, 6, 0, '이용 문의', '처음 이용하는데 절차가 궁금합니다.', 1, '회원가입 후 이용 가능합니다.', 2, NOW(), NOW(), NOW()),
(10, 7, 1, '환불 문의', '중도 환불 가능한가요?', 0, '', 0, NOW(), NOW(), NOW());

-- 31. rockerusage_tb (락커 사용 내역 테이블)
INSERT INTO rockerusage_tb (ru_rocker, ru_user, ru_membership, ru_startdate, ru_enddate, ru_status, ru_deposit, ru_monthlyfee, ru_note, ru_assignedby, ru_assigneddate, ru_date) VALUES
(3, 5, 1, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 1, 50000, 30000, '남자 락커 101', 2, NOW(), NOW()),
(7, 6, 2, NOW(), DATE_ADD(NOW(), INTERVAL 90 DAY), 1, 50000, 30000, '여자 락커 201', 2, NOW(), NOW()),
(6, 7, 3, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 1, 50000, 30000, '남자 락커 102', 2, NOW(), NOW()),
(8, 8, 4, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 1, 50000, 30000, '여자 락커 202', 2, NOW(), NOW()),
(9, 10, 5, NOW(), DATE_ADD(NOW(), INTERVAL 60 DAY), 1, 50000, 30000, '남자 락커 103', 2, NOW(), NOW()),
(1, 5, 6, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 1, 50000, 30000, '락커 재등록', 2, NOW(), NOW()),
(4, 6, 7, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 1, 50000, 30000, '락커 사용', 2, NOW(), NOW()),
(2, 7, 8, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 0, 50000, 30000, '만료됨', 2, DATE_SUB(NOW(), INTERVAL 5 DAY), NOW()),
(5, 8, 9, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 1, 50000, 30000, '락커 사용', 2, NOW(), NOW()),
(10, 10, 10, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 2, 50000, 30000, '연체 상태', 2, DATE_SUB(NOW(), INTERVAL 10 DAY), NOW());

-- 32. pushtoken_tb (푸시 토큰 테이블)
INSERT INTO pushtoken_tb (pt_user, pt_token, pt_devicetype, pt_deviceid, pt_appversion, pt_isactive, pt_createddate, pt_date) VALUES
(5, 'fcm_token_member1_ios', 'ios', 'device_id_001', '1.0.0', 1, NOW(), NOW()),
(6, 'fcm_token_member2_android', 'android', 'device_id_002', '1.0.1', 1, NOW(), NOW()),
(7, 'fcm_token_member3_ios', 'ios', 'device_id_003', '1.0.0', 1, NOW(), NOW()),
(8, 'fcm_token_member4_android', 'android', 'device_id_004', '1.0.1', 1, NOW(), NOW()),
(10, 'fcm_token_member5_ios', 'ios', 'device_id_005', '1.0.2', 1, NOW(), NOW()),
(5, 'fcm_token_member1_old', 'ios', 'device_id_001_old', '0.9.0', 0, DATE_SUB(NOW(), INTERVAL 30 DAY), NOW()),
(6, 'fcm_token_member2_old', 'android', 'device_id_002_old', '0.9.0', 0, DATE_SUB(NOW(), INTERVAL 30 DAY), NOW()),
(3, 'fcm_token_trainer1', 'ios', 'device_id_006', '1.0.0', 1, NOW(), NOW()),
(4, 'fcm_token_trainer2', 'android', 'device_id_007', '1.0.1', 1, NOW(), NOW()),
(9, 'fcm_token_staff1', 'ios', 'device_id_008', '1.0.0', 1, NOW(), NOW());

-- 33. appversion_tb (앱 버전 테이블)
INSERT INTO appversion_tb (av_platform, av_version, av_minversion, av_forceupdate, av_updatemessage, av_downloadurl, av_status, av_releasedate, av_createddate, av_date) VALUES
('android', '1.0.0', '1.0.0', 0, '최초 출시 버전', 'https://play.google.com/store/apps/gym', 1, NOW(), NOW(), NOW()),
('ios', '1.0.0', '1.0.0', 0, '최초 출시 버전', 'https://apps.apple.com/app/gym', 1, NOW(), NOW(), NOW()),
('android', '1.0.1', '1.0.0', 0, '버그 수정 및 성능 개선', 'https://play.google.com/store/apps/gym', 1, NOW(), NOW(), NOW()),
('ios', '1.0.1', '1.0.0', 0, '버그 수정 및 성능 개선', 'https://apps.apple.com/app/gym', 1, NOW(), NOW(), NOW()),
('android', '1.0.2', '1.0.0', 0, '새로운 기능 추가', 'https://play.google.com/store/apps/gym', 1, NOW(), NOW(), NOW()),
('ios', '1.0.2', '1.0.0', 0, '새로운 기능 추가', 'https://apps.apple.com/app/gym', 1, NOW(), NOW(), NOW()),
('android', '1.1.0', '1.0.0', 1, '중요 업데이트 - 필수', 'https://play.google.com/store/apps/gym', 1, NOW(), NOW(), NOW()),
('ios', '1.1.0', '1.0.0', 1, '중요 업데이트 - 필수', 'https://apps.apple.com/app/gym', 1, NOW(), NOW(), NOW()),
('android', '0.9.0', '0.9.0', 0, '베타 버전', 'https://play.google.com/store/apps/gym', 0, DATE_SUB(NOW(), INTERVAL 60 DAY), NOW(), NOW()),
('ios', '0.9.0', '0.9.0', 0, '베타 버전', 'https://apps.apple.com/app/gym', 0, DATE_SUB(NOW(), INTERVAL 60 DAY), NOW(), NOW());

-- 34. workoutlog_tb (운동 기록 테이블)
INSERT INTO workoutlog_tb (wl_user, wl_attendance, wl_health, wl_exercisename, wl_sets, wl_reps, wl_weight, wl_duration, wl_calories, wl_note, wl_date) VALUES
(5, 1, 1, '벤치프레스', 3, 10, 60.0, 30, 150, '가슴 운동', NOW()),
(5, 1, 1, '스쿼트', 4, 12, 80.0, 40, 200, '하체 운동', NOW()),
(6, 2, 2, '데드리프트', 3, 8, 70.0, 25, 180, '등 운동', NOW()),
(7, 3, 3, '숄더프레스', 3, 10, 40.0, 20, 120, '어깨 운동', NOW()),
(8, 4, 4, '레그프레스', 4, 15, 100.0, 35, 220, '하체 강화', NOW()),
(10, 5, 5, '랫풀다운', 3, 12, 50.0, 25, 140, '등 운동', NOW()),
(5, 1, 1, '벤치프레스', 3, 10, 65.0, 30, 155, '중량 증가', NOW()),
(6, 2, 2, '스쿼트', 4, 12, 85.0, 40, 210, '중량 증가', NOW()),
(7, 3, 3, '데드리프트', 3, 8, 75.0, 25, 190, '중량 증가', NOW()),
(8, 4, 4, '숄더프레스', 3, 10, 45.0, 20, 130, '중량 증가', NOW());

-- 35. membershipusage_tb (회원권 사용 내역 테이블)
INSERT INTO membershipusage_tb (mu_membership, mu_user, mu_type, mu_totaldays, mu_useddays, mu_remainingdays, mu_totalcount, mu_usedcount, mu_remainingcount, mu_startdate, mu_enddate, mu_status, mu_lastuseddate, mu_date) VALUES
(1, 5, 0, 30, 5, 25, 0, 0, 0, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 0, NOW(), NOW()),
(2, 6, 0, 90, 12, 78, 0, 0, 0, NOW(), DATE_ADD(NOW(), INTERVAL 90 DAY), 0, NOW(), NOW()),
(3, 7, 1, 0, 0, 0, 10, 1, 9, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 0, NOW(), NOW()),
(4, 8, 0, 30, 8, 22, 0, 0, 0, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 0, NOW(), NOW()),
(5, 10, 0, 30, 3, 27, 0, 0, 0, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 0, NOW(), NOW()),
(6, 5, 0, 30, 2, 28, 0, 0, 0, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 0, DATE_ADD(NOW(), INTERVAL 1 DAY), NOW()),
(7, 6, 0, 30, 1, 29, 0, 0, 0, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 0, DATE_ADD(NOW(), INTERVAL 1 DAY), NOW()),
(8, 7, 1, 0, 0, 0, 10, 2, 8, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 0, DATE_ADD(NOW(), INTERVAL 2 DAY), NOW()),
(9, 8, 0, 30, 4, 26, 0, 0, 0, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 0, DATE_ADD(NOW(), INTERVAL 2 DAY), NOW()),
(10, 10, 0, 30, 1, 29, 0, 0, 0, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 0, DATE_ADD(NOW(), INTERVAL 3 DAY), NOW());

-- 완료
SELECT 'Sample data inserted successfully!' AS message;

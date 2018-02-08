-- phpMyAdmin SQL Dump
-- version 4.0.10.18
-- https://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Feb 08, 2018 at 12:43 AM
-- Server version: 5.6.36-cll-lve
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `newsspaas`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_login`
--

CREATE TABLE IF NOT EXISTS `admin_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_uname` varchar(50) NOT NULL,
  `admin_password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_uname` (`admin_uname`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin_login`
--

INSERT INTO `admin_login` (`id`, `admin_uname`, `admin_password`) VALUES
(1, 's', 's');

-- --------------------------------------------------------

--
-- Table structure for table `apps_countries`
--

CREATE TABLE IF NOT EXISTS `apps_countries` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country_code` varchar(2) NOT NULL DEFAULT '',
  `country_name` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=246 ;

--
-- Dumping data for table `apps_countries`
--

INSERT INTO `apps_countries` (`id`, `country_code`, `country_name`) VALUES
(1, 'AF', 'Afghanistan'),
(2, 'AL', 'Albania'),
(3, 'DZ', 'Algeria'),
(4, 'DS', 'American Samoa'),
(5, 'AD', 'Andorra'),
(6, 'AO', 'Angola'),
(7, 'AI', 'Anguilla'),
(8, 'AQ', 'Antarctica'),
(9, 'AG', 'Antigua and Barbuda'),
(10, 'AR', 'Argentina'),
(11, 'AM', 'Armenia'),
(12, 'AW', 'Aruba'),
(13, 'AU', 'Australia'),
(14, 'AT', 'Austria'),
(15, 'AZ', 'Azerbaijan'),
(16, 'BS', 'Bahamas'),
(17, 'BH', 'Bahrain'),
(18, 'BD', 'Bangladesh'),
(19, 'BB', 'Barbados'),
(20, 'BY', 'Belarus'),
(21, 'BE', 'Belgium'),
(22, 'BZ', 'Belize'),
(23, 'BJ', 'Benin'),
(24, 'BM', 'Bermuda'),
(25, 'BT', 'Bhutan'),
(26, 'BO', 'Bolivia'),
(27, 'BA', 'Bosnia and Herzegovina'),
(28, 'BW', 'Botswana'),
(29, 'BV', 'Bouvet Island'),
(30, 'BR', 'Brazil'),
(31, 'IO', 'British Indian Ocean Territory'),
(32, 'BN', 'Brunei Darussalam'),
(33, 'BG', 'Bulgaria'),
(34, 'BF', 'Burkina Faso'),
(35, 'BI', 'Burundi'),
(36, 'KH', 'Cambodia'),
(37, 'CM', 'Cameroon'),
(38, 'CA', 'Canada'),
(39, 'CV', 'Cape Verde'),
(40, 'KY', 'Cayman Islands'),
(41, 'CF', 'Central African Republic'),
(42, 'TD', 'Chad'),
(43, 'CL', 'Chile'),
(44, 'CN', 'China'),
(45, 'CX', 'Christmas Island'),
(46, 'CC', 'Cocos (Keeling) Islands'),
(47, 'CO', 'Colombia'),
(48, 'KM', 'Comoros'),
(49, 'CG', 'Congo'),
(50, 'CK', 'Cook Islands'),
(51, 'CR', 'Costa Rica'),
(52, 'HR', 'Croatia (Hrvatska)'),
(53, 'CU', 'Cuba'),
(54, 'CY', 'Cyprus'),
(55, 'CZ', 'Czech Republic'),
(56, 'DK', 'Denmark'),
(57, 'DJ', 'Djibouti'),
(58, 'DM', 'Dominica'),
(59, 'DO', 'Dominican Republic'),
(60, 'TP', 'East Timor'),
(61, 'EC', 'Ecuador'),
(62, 'EG', 'Egypt'),
(63, 'SV', 'El Salvador'),
(64, 'GQ', 'Equatorial Guinea'),
(65, 'ER', 'Eritrea'),
(66, 'EE', 'Estonia'),
(67, 'ET', 'Ethiopia'),
(68, 'FK', 'Falkland Islands (Malvinas)'),
(69, 'FO', 'Faroe Islands'),
(70, 'FJ', 'Fiji'),
(71, 'FI', 'Finland'),
(72, 'FR', 'France'),
(73, 'FX', 'France, Metropolitan'),
(74, 'GF', 'French Guiana'),
(75, 'PF', 'French Polynesia'),
(76, 'TF', 'French Southern Territories'),
(77, 'GA', 'Gabon'),
(78, 'GM', 'Gambia'),
(79, 'GE', 'Georgia'),
(80, 'DE', 'Germany'),
(81, 'GH', 'Ghana'),
(82, 'GI', 'Gibraltar'),
(83, 'GK', 'Guernsey'),
(84, 'GR', 'Greece'),
(85, 'GL', 'Greenland'),
(86, 'GD', 'Grenada'),
(87, 'GP', 'Guadeloupe'),
(88, 'GU', 'Guam'),
(89, 'GT', 'Guatemala'),
(90, 'GN', 'Guinea'),
(91, 'GW', 'Guinea-Bissau'),
(92, 'GY', 'Guyana'),
(93, 'HT', 'Haiti'),
(94, 'HM', 'Heard and Mc Donald Islands'),
(95, 'HN', 'Honduras'),
(96, 'HK', 'Hong Kong'),
(97, 'HU', 'Hungary'),
(98, 'IS', 'Iceland'),
(99, 'IN', 'India'),
(100, 'IM', 'Isle of Man'),
(101, 'ID', 'Indonesia'),
(102, 'IR', 'Iran (Islamic Republic of)'),
(103, 'IQ', 'Iraq'),
(104, 'IE', 'Ireland'),
(105, 'IL', 'Israel'),
(106, 'IT', 'Italy'),
(107, 'CI', 'Ivory Coast'),
(108, 'JE', 'Jersey'),
(109, 'JM', 'Jamaica'),
(110, 'JP', 'Japan'),
(111, 'JO', 'Jordan'),
(112, 'KZ', 'Kazakhstan'),
(113, 'KE', 'Kenya'),
(114, 'KI', 'Kiribati'),
(115, 'KP', 'Korea, Democratic People''s Republic of'),
(116, 'KR', 'Korea, Republic of'),
(117, 'XK', 'Kosovo'),
(118, 'KW', 'Kuwait'),
(119, 'KG', 'Kyrgyzstan'),
(120, 'LA', 'Lao People''s Democratic Republic'),
(121, 'LV', 'Latvia'),
(122, 'LB', 'Lebanon'),
(123, 'LS', 'Lesotho'),
(124, 'LR', 'Liberia'),
(125, 'LY', 'Libyan Arab Jamahiriya'),
(126, 'LI', 'Liechtenstein'),
(127, 'LT', 'Lithuania'),
(128, 'LU', 'Luxembourg'),
(129, 'MO', 'Macau'),
(130, 'MK', 'Macedonia'),
(131, 'MG', 'Madagascar'),
(132, 'MW', 'Malawi'),
(133, 'MY', 'Malaysia'),
(134, 'MV', 'Maldives'),
(135, 'ML', 'Mali'),
(136, 'MT', 'Malta'),
(137, 'MH', 'Marshall Islands'),
(138, 'MQ', 'Martinique'),
(139, 'MR', 'Mauritania'),
(140, 'MU', 'Mauritius'),
(141, 'TY', 'Mayotte'),
(142, 'MX', 'Mexico'),
(143, 'FM', 'Micronesia, Federated States of'),
(144, 'MD', 'Moldova, Republic of'),
(145, 'MC', 'Monaco'),
(146, 'MN', 'Mongolia'),
(147, 'ME', 'Montenegro'),
(148, 'MS', 'Montserrat'),
(149, 'MA', 'Morocco'),
(150, 'MZ', 'Mozambique'),
(151, 'MM', 'Myanmar'),
(152, 'NA', 'Namibia'),
(153, 'NR', 'Nauru'),
(154, 'NP', 'Nepal'),
(155, 'NL', 'Netherlands'),
(156, 'AN', 'Netherlands Antilles'),
(157, 'NC', 'New Caledonia'),
(158, 'NZ', 'New Zealand'),
(159, 'NI', 'Nicaragua'),
(160, 'NE', 'Niger'),
(161, 'NG', 'Nigeria'),
(162, 'NU', 'Niue'),
(163, 'NF', 'Norfolk Island'),
(164, 'MP', 'Northern Mariana Islands'),
(165, 'NO', 'Norway'),
(166, 'OM', 'Oman'),
(167, 'PK', 'Pakistan'),
(168, 'PW', 'Palau'),
(169, 'PS', 'Palestine'),
(170, 'PA', 'Panama'),
(171, 'PG', 'Papua New Guinea'),
(172, 'PY', 'Paraguay'),
(173, 'PE', 'Peru'),
(174, 'PH', 'Philippines'),
(175, 'PN', 'Pitcairn'),
(176, 'PL', 'Poland'),
(177, 'PT', 'Portugal'),
(178, 'PR', 'Puerto Rico'),
(179, 'QA', 'Qatar'),
(180, 'RE', 'Reunion'),
(181, 'RO', 'Romania'),
(182, 'RU', 'Russian Federation'),
(183, 'RW', 'Rwanda'),
(184, 'KN', 'Saint Kitts and Nevis'),
(185, 'LC', 'Saint Lucia'),
(186, 'VC', 'Saint Vincent and the Grenadines'),
(187, 'WS', 'Samoa'),
(188, 'SM', 'San Marino'),
(189, 'ST', 'Sao Tome and Principe'),
(190, 'SA', 'Saudi Arabia'),
(191, 'SN', 'Senegal'),
(192, 'RS', 'Serbia'),
(193, 'SC', 'Seychelles'),
(194, 'SL', 'Sierra Leone'),
(195, 'SG', 'Singapore'),
(196, 'SK', 'Slovakia'),
(197, 'SI', 'Slovenia'),
(198, 'SB', 'Solomon Islands'),
(199, 'SO', 'Somalia'),
(200, 'ZA', 'South Africa'),
(201, 'GS', 'South Georgia South Sandwich Islands'),
(202, 'ES', 'Spain'),
(203, 'LK', 'Sri Lanka'),
(204, 'SH', 'St. Helena'),
(205, 'PM', 'St. Pierre and Miquelon'),
(206, 'SD', 'Sudan'),
(207, 'SR', 'Suriname'),
(208, 'SJ', 'Svalbard and Jan Mayen Islands'),
(209, 'SZ', 'Swaziland'),
(210, 'SE', 'Sweden'),
(211, 'CH', 'Switzerland'),
(212, 'SY', 'Syrian Arab Republic'),
(213, 'TW', 'Taiwan'),
(214, 'TJ', 'Tajikistan'),
(215, 'TZ', 'Tanzania, United Republic of'),
(216, 'TH', 'Thailand'),
(217, 'TG', 'Togo'),
(218, 'TK', 'Tokelau'),
(219, 'TO', 'Tonga'),
(220, 'TT', 'Trinidad and Tobago'),
(221, 'TN', 'Tunisia'),
(222, 'TR', 'Turkey'),
(223, 'TM', 'Turkmenistan'),
(224, 'TC', 'Turks and Caicos Islands'),
(225, 'TV', 'Tuvalu'),
(226, 'UG', 'Uganda'),
(227, 'UA', 'Ukraine'),
(228, 'AE', 'United Arab Emirates'),
(229, 'GB', 'United Kingdom'),
(230, 'US', 'United States'),
(231, 'UM', 'United States minor outlying islands'),
(232, 'UY', 'Uruguay'),
(233, 'UZ', 'Uzbekistan'),
(234, 'VU', 'Vanuatu'),
(235, 'VA', 'Vatican City State'),
(236, 'VE', 'Venezuela'),
(237, 'VN', 'Vietnam'),
(238, 'VG', 'Virgin Islands (British)'),
(239, 'VI', 'Virgin Islands (U.S.)'),
(240, 'WF', 'Wallis and Futuna Islands'),
(241, 'EH', 'Western Sahara'),
(242, 'YE', 'Yemen'),
(243, 'ZR', 'Zaire'),
(244, 'ZM', 'Zambia'),
(245, 'ZW', 'Zimbabwe');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE IF NOT EXISTS `feedback` (
  `id` int(11) NOT NULL,
  `message` varchar(1000) NOT NULL,
  `mobile` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `web` varchar(255) NOT NULL,
  `mobile_no` varchar(255) NOT NULL,
  `office_no` varchar(255) NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(11) NOT NULL DEFAULT '0',
  `role` varchar(155) NOT NULL,
  `school_name` varchar(255) NOT NULL,
  `otp` varchar(10) NOT NULL,
  PRIMARY KEY (`login_id`),
  UNIQUE KEY `user_name` (`user_name`),
  KEY `user_name_3` (`user_name`),
  FULLTEXT KEY `user_name_2` (`user_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=71 ;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`login_id`, `group_id`, `first_name`, `last_name`, `email`, `user_name`, `password`, `web`, `mobile_no`, `office_no`, `updated_at`, `status`, `role`, `school_name`, `otp`) VALUES
(1, 1, 'Saurbh', 'Sharma', 'admin@gmail.com', 'admin', '321', 'http://www.ssappsnwebs.com', '7869234669', '07314095131', '2017-08-29 18:33:21', 1, '', 'N/A', ''),
(48, 3, '', '', '', '', '', '', '', '', '2017-10-29 11:02:29', 1, 'school', '1', '417143'),
(49, 3, 'cvs school', '', 'cvs@cvs.com', 'cvsuser', 'cvs', 'w.t.s', '2121212121212', '2121212121212', '2017-10-29 11:06:14', 1, 'school', '3', ''),
(50, 3, 'mitali', '', 'ssappswebs@gmail.com', 'mitali', '111', 'www.ssappsnwebs.com', '7777777777777', '7777777777777', '2017-10-29 11:49:57', 1, 'school', '5', '741755'),
(52, 3, 'cvs school', '', '1', 'test', 'cvs', 'w.t.s', '4.44E+12', '4.44E+12', '2017-10-29 14:31:12', 1, 'school', '7', ''),
(55, 3, 'EM convent', '', 'em@gmail.com', 'em', '123', 'www.ssappsnwebs.com', '8888888888888', '8888888888888', '2017-10-30 11:02:57', 1, 'school', '18', ''),
(56, 3, 'aaadsdd', '', 'dasdsa@sadsa.dsadasd', 'adasdasd', 'aaa', 's.s.s', '2222222222222', '2222222222222', '2017-10-30 11:03:04', 1, 'school', '19', ''),
(57, 3, 'sss', '', 'dsadsd@safa.dd', 'aasss', 'ddd', 'sdasd', '4444444444444', '4444444444444', '2017-10-30 11:29:31', 1, 'school', '20', ''),
(58, 3, 'sss', '', 'sadsdsa@dasd.sd', 'sdasd', 'sda', 'aaa', '1111111111111', '1111111111111', '2017-10-30 11:31:37', 1, 'school', '21', ''),
(59, 3, 'adas', '', 'ssdasd@dsad.dsad', '2222222234sa', 'dad', 'sdsa', '1111111111111', '1111111111111', '2017-10-30 11:50:45', 1, 'school', '22', ''),
(60, 3, 'oo school', '', 'e@ff.com', 'oo', 'oo', 'bb@ee.com', '09293838', '09293838', '2017-10-30 13:51:26', 1, 'school', '23', ''),
(61, 3, 'll high school', '', 'ee@ee.com', 'll', 'll', 'll@ll.org', '882992992', '882992992', '2017-10-30 13:53:34', 1, 'school', '24', ''),
(62, 3, 'kk school', '', 'kk@kk.com', 'kk', 'kk', 'kk.com', '1232434', '1232434', '2017-10-30 19:37:38', 1, 'school', '25', ''),
(63, 3, 'Gaurav Mourya', '', 'gauravmouryaweb@gmail.com', 'gaurav', '123', 's.s.s', '8878765084', '8878765084', '2017-11-08 12:52:23', 1, 'school', '26', ''),
(64, 3, 'Gayaza High School', '', 'ghs@ghs.com', 'ghs', 'ghs', 'ghs.com', '01231234555', '01231234555', '2017-11-08 16:40:35', 1, 'school', '27', ''),
(65, 3, 'Mukono Secondary School', '', 'mss@gmail.com', 'mss', 'mss', 'none', '32145698', '32145698', '2017-11-08 16:46:52', 1, 'school', '28', ''),
(66, 3, 'Gayaza Nursery School', '', 'gns@gns.com', 'gns', 'gns', '', '65498712', '65498712', '2017-11-08 16:48:29', 1, 'school', '29', ''),
(67, 3, 'vishal', '', 'v@v.vom', 'vishal', 'vishal', 'www.abc.com', '999999999999', '999999999999', '2017-12-22 11:23:10', 1, 'school', '30', ''),
(68, 3, 'vishal', '', 'vv@v.vom', 'vishals', 'vishal', 'www.abc.com', '999999999999', '999999999999', '2017-12-22 11:23:38', 1, 'school', '31', ''),
(69, 3, 'vishal', '', 'vv@v.com', 'www12', 'vishal', 'www.abc.com', '999999999999', '999999999999', '2017-12-22 11:24:16', 1, 'school', '32', ''),
(70, 3, 'wq', '', 'wq@wq.com', 'wq', 'wq', 'www.ccccc.com', '999999999999', '9', '2017-12-22 11:26:29', 1, 'school', 'N/A', '');

-- --------------------------------------------------------

--
-- Table structure for table `news_feed`
--

CREATE TABLE IF NOT EXISTS `news_feed` (
  `new_id` int(11) NOT NULL AUTO_INCREMENT,
  `news` text NOT NULL,
  `news_type` varchar(50) NOT NULL,
  `news_class` varchar(50) NOT NULL,
  `news_time` time NOT NULL,
  `news_date` date NOT NULL,
  `url` varchar(255) NOT NULL,
  `news_status` int(11) NOT NULL,
  `school_id` int(11) NOT NULL,
  PRIMARY KEY (`new_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=308 ;

--
-- Dumping data for table `news_feed`
--

INSERT INTO `news_feed` (`new_id`, `news`, `news_type`, `news_class`, `news_time`, `news_date`, `url`, `news_status`, `school_id`) VALUES
(3, 'Annual Function will be held on 30th Oct 17.', 'general', '', '00:00:00', '2018-09-30', '', 0, 14),
(5, '2nd oct Holiday of Gandhi Jayanti', 'general', '', '00:00:00', '2017-10-02', '', 0, 16),
(6, 'science exhibition', 'personal', '18', '00:00:00', '2017-10-05', '', 0, 16),
(7, 'Games Day\r\n', 'personal', '18', '00:00:00', '2017-10-10', '', 0, 16),
(13, 'Red Day Celebration', 'general', '', '00:00:00', '2017-10-14', '', 0, 20),
(14, 'White Uniform', 'personal', '21', '00:00:00', '2017-10-13', '', 0, 20),
(15, 'Board Exam Practice Test', 'general', '', '00:00:00', '2017-10-21', '', 0, 21),
(21, 'personal new updates', 'personal', '16,17', '00:00:00', '2017-10-08', '1507468762.png', 0, 14),
(23, 'Announcment 1', 'general', '', '00:00:00', '2017-10-09', '1507472725.jpg', 0, 14),
(24, 'testing jpg', 'general', '', '00:00:00', '2017-10-08', '1507472725.jpg', 0, 14),
(25, 'testing jpg 2', 'general', '', '00:00:00', '2017-10-08', '1507472725.jpg', 0, 14),
(26, 'happy indipendence day celebrations', 'general', '', '00:00:00', '2017-10-09', '', 0, 14),
(27, 'last school day, come pick your child. good holidays', 'general', '', '00:00:00', '2017-10-14', '', 0, 14),
(28, 'hdhdhdhdhdhd', 'general', '', '00:00:00', '2017-10-13', '', 0, 14),
(29, 'Sports Day ', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(30, 'Tomorrow bring your hindi notebook', 'personal', '26', '00:00:00', '2017-10-14', '', 0, 23),
(31, 'yelllo day celebration', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(32, 'Latest News ', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(33, 'this is new one', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(34, 'dfsgsg', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(35, 'dfsgsg', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(36, 'dfsgsg test', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(37, 'dfsgsg test', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(38, 'tersdyt', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(39, 'test', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(40, 'ghhfghfg', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(41, 'ghhfghfg test', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(42, 'ghhfghfg test arpit', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(43, 'ghhfghfg test arpit123', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(44, 'test', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(45, 'test test', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(46, 'test', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(47, 'test1', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(48, 'test', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(49, 'test', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(50, 'test', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(51, 'test', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(52, 'test', 'personal', '26', '00:00:00', '2017-10-14', '', 0, 23),
(53, 'test', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(54, 'test', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(55, 'test23', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(56, 'test', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(57, 'please test  general news', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(58, 'please t est news', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(59, 'test news', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(60, 'test', 'general', '', '00:00:00', '2017-10-14', '1507997000.jpg', 0, 23),
(61, 'test surabh', 'general', '', '00:00:00', '2017-10-14', '', 0, 23),
(62, 'test news ssdlfjdslfjds Android is a mobile operating system developed by Google, based on the Linux kernel and designed primarily for touchscreen mobile devices such as smartphones and tablets. Android''s user interface is mainly based on direct manipulation, using touch gestures that loosely correspond to real-world actions, such as swiping, tapping and pinching, to manipulate on-screen objects, along with a virtual keyboard for text input. In addition to touchscreen devices, Google has further developed Android TV for televisions, Android Auto for cars, and Android Wear for wrist watches, each with a specialized user interface. Variants of Android are also used on game consoles, digital cameras, PCs and other electronics.\r\n\r\nInitially developed by Android Inc., which Google bought in 2005, Android was unveiled in 2007, along with the founding of the Open Handset Alliance – a consortium of hardware, software, and telecommunication companies devoted to advancing open standards for mobile devices. Beginning with the first commercial Android device in September 2008, the operating system has gone through multiple major releases, with the current version being 8.0 "Oreo", released in August 2017. Android applications ("apps") can be downloaded from the Google Play store, which features over 2.7 million apps as of February 2017. Android has been the best-selling OS on tablets since 2013, and runs on the vast majority[a] of smartphones. As of May 2017, Android has two billion monthly active users, and it has the largest installed base of any operating system.', 'general', '', '00:00:00', '2017-10-15', '', 0, 14),
(64, 'Android is a mobile operating system developed by Google, based on the Linux kernel and designed primarily for touchscreen mobile devices such as smartphones and tablets. Android''s user interface is mainly ', 'general', '', '00:00:00', '2017-10-15', '', 0, 14),
(66, 'test arpit', 'general', '', '00:00:00', '2017-10-15', '', 0, 14),
(67, 'testing time field', 'general', '', '12:45:00', '2017-10-15', '1508052288.jpg', 0, 14),
(68, 'testing time field', 'general', '', '12:45:00', '2017-10-15', '1508052288.jpg', 0, 14),
(69, 'testing another news', 'general', '', '13:00:00', '2017-10-25', '1508052504.jpg', 0, 14),
(70, 'arpita', 'general', '', '14:00:00', '2017-10-30', '1508052642.jpg', 0, 14),
(71, 'arpita', 'general', '', '13:15:00', '2017-10-15', '', 0, 14),
(72, 'image testing.', 'general', '', '13:15:00', '2017-10-15', '1508052747.jpg', 0, 14),
(73, '                                               Post your news here', 'general', '', '15:45:00', '2017-10-15', '1508062426.jpg', 0, 14),
(74, '                                               Post your news here...\n                                          ', 'general', '', '15:45:00', '2017-10-15', '1508062608.jpg', 0, 14),
(75, 'text area testing.....', 'general', '', '08:45:00', '2017-10-16', '1508123455.jpg', 0, 14),
(78, 'testing push....', 'general', '', '09:15:00', '2017-10-17', '1508211283.png', 0, 14),
(79, '<p>widgEditor <strong>automatically</strong> integrates the content that was in the textarea!</p>\r\n<p>jsjsjsjsj''a.''&nbsp;A,;dsmf&nbsp;&gt;d&nbsp;mcxzxxxxxxx</p>', 'general', '', '13:55:00', '2017-10-17', '', 0, 24),
(80, '<p>widgEditor <strong>automatically</strong> integrates the content that was in the textarea!</p>', 'general', '', '14:00:00', '2017-10-17', '', 0, 24),
(81, '<p>widgEditor <strong>automatically</strong> integrates the content that was in the textarea!</p>', 'general', '', '14:00:00', '2017-10-17', '', 0, 24),
(82, '<p>yello day</p>', 'general', '', '14:00:00', '2017-10-17', '', 0, 24),
(83, '<p>widgEditor <strong>automatically</strong> integrates the content that was in the textarea!</p>', 'general', '', '14:00:00', '2017-10-17', '', 0, 24),
(85, '<p>Tomorrow bring your maths notebook.</p>', 'personal', '28', '16:00:00', '2017-10-17', '1508235804.jpg', 0, 24),
(86, '<p>widgEditor <strong>automatically</strong> integrates the content that was in the textarea!</p>', 'general', '', '14:30:00', '2017-10-17', '1508239253.png', 0, 14),
(87, '<p>widgEditor <strong>automatically</strong> integrates the content that was in the textarea!</p>', 'general', '', '14:30:00', '2017-10-17', '1508239308.jpg', 0, 14),
(90, '<p><span style="background-color: yellow;">sdasda</span></p>\r\n<p><span style="background-color: yellow;">sddasdas</span></p>\r\n<p><span style="background-color: yellow;">dasda</span></p>', 'general', '', '12:00:00', '2017-10-24', '', 0, 14),
(91, '<p><span style="background-color: yellow;">sdasda</span></p>\r\n<p><span style="background-color: yellow;">sddasdas</span></p>\r\n<p><span style="background-color: yellow;">dasda</span></p>', 'general', '', '12:00:00', '2017-10-24', '', 0, 14),
(94, '<h1><span style="font-family: &quot;Comic Sans MS&quot;;"><span style="font-size: large;">Title</span></span></h1>\r\n<p>&nbsp;</p>\r\n<p>This is news</p>', 'general', '', '12:00:00', '2017-10-24', '', 0, 14),
(99, '<p><img src="http://localhost/admin2/assets/fckeditor/editor/images/smiley/msn/confused_smile.gif" alt="" /></p>\r\n<p>&nbsp;</p>\r\n<h1><span style="font-size: large;"><span style="font-family: &quot;Comic Sans MS&quot;;">sadasdadas</span></span></h1>', 'general', '', '12:00:00', '2017-10-24', '', 0, 14),
(102, '<h2>sdasdasdasdasdas</h2>', 'general', '', '12:00:00', '2017-10-24', '', 0, 14),
(105, 'Type Page Content Here...', 'general', '', '12:15:00', '2017-10-24', '', 0, 14),
(106, '<p>&lt;html&gt;</p>\r\n<p>&lt;body&gt;</p>\r\n<p>&lt;h1&gt;asdasdasdasd&lt;/h1&gt;</p>\r\n<p>&lt;/body&gt;</p>\r\n<p>&lt;/html&gt;</p>', 'general', '', '12:15:00', '2017-10-24', '', 0, 14),
(119, '<p>&nbsp;dasdasd</p>', 'general', '', '13:15:00', '2017-10-24', '1508830519.png', 0, 27),
(123, '<p>&nbsp;adsadasdasdasddasd</p>', 'general', '', '16:45:00', '2017-10-24', '1508843753.mp4', 0, 36),
(125, '<p>News 1 from Mitali school with jpg</p>', 'general', '', '18:10:00', '2017-10-24', '1508848372.mp4', 0, 37),
(128, '<p>&nbsp;This is <strong>jpg.</strong></p>', 'general', '', '19:25:00', '2017-10-24', '1508853114.jpg', 0, 40),
(129, '<p>&nbsp;This is the video<img src="http://www.ssappsnwebs.com/spass/admin2/assets/fckeditor/editor/images/smiley/msn/wink_smile.gif" alt="" /></p>', 'general', '', '19:30:00', '2017-10-24', '1508853213.mp4', 0, 40),
(131, '<p>&nbsp;Hello this will be the competetion going to held on.<img src="http://www.ssappsnwebs.com/spass/admin2/assets/fckeditor/editor/images/smiley/msn/teeth_smile.gif" alt="" /></p>', 'general', '', '16:45:00', '2017-10-25', '1508929909.jpg', 0, 37),
(132, '<p>&nbsp;video 3</p>', 'general', '', '16:45:00', '2017-10-25', '1508930029.mp4', 0, 37),
(133, '<p>&nbsp;Hello, Please go through the video.</p>', 'personal', '31,32', '17:25:00', '2017-10-25', '1508932485.mp4', 0, 37),
(134, '<p>&nbsp;Have a look at the image.<img src="http://www.ssappsnwebs.com/spass/admin2/assets/fckeditor/editor/images/smiley/msn/sad_smile.gif" alt="" /></p>', 'personal', '30', '18:50:00', '2017-10-25', '1508933931.jpg', 0, 37),
(135, '<p>&nbsp;There is something.<img src="http://www.ssappsnwebs.com/spass/admin2/assets/fckeditor/editor/images/smiley/msn/tounge_smile.gif" alt="" /></p>', 'personal', '31', '18:55:00', '2017-10-25', '1508934292.mp4', 0, 37),
(136, '<p>Special Video 4.</p>', 'personal', '32', '18:30:00', '2017-10-25', '1508936323.mp4', 0, 37),
(137, '<p>hello africa tell me how you are doing</p>', 'general', '', '18:30:00', '2017-10-25', '1508945007.jpg', 0, 41),
(138, '<p>This weekend is visitaiton day, parents are all invited to be at school at esactly 9AM with their children. School Admin</p>', 'general', '', '08:45:00', '2017-10-28', '', 0, 41),
(139, '<p>Test fonts &amp; <span style="font-size: large;"><span style="background-color: yellow;">formats</span></span></p>', 'general', '', '21:00:00', '2017-10-27', '', 0, 41),
(140, '<p>&nbsp;hello news</p>', 'general', '', '12:00:00', '2017-10-28', '1509172121.jpg', 0, 37),
(141, '<p>&nbsp;fdsfsdfdasd ggg</p>', 'general', '', '00:15:00', '2017-10-28', '', 0, 37),
(142, '<p>&nbsp;sadasd</p>', 'general', '', '12:15:00', '2017-10-28', '1509173025.mp4', 0, 37),
(143, '<p>&nbsp;This is video of 130 pm</p>', 'general', '', '13:35:00', '2017-10-28', '1509177586.mp4', 0, 37),
(144, '<p>&nbsp;dasdasd</p>', 'general', '', '14:45:00', '2017-10-28', '1509181339.mp4', 0, 37),
(145, '<p>&nbsp;news from fatema with video at 2.40 PM</p>', 'general', '', '14:42:00', '2017-10-28', '1509181903.mp4', 0, 42),
(146, '<p>&nbsp;fatema new news video</p>', 'general', '', '14:45:00', '2017-10-28', '1509182103.mp4', 0, 42),
(147, '<p>&nbsp;safdsf</p>', 'general', '', '13:45:00', '2017-10-29', '1509264371.png', 0, 37),
(148, '<p>&nbsp;test newss</p>', 'general', '', '17:30:00', '2017-10-29', '', 0, 5),
(149, '<p>&nbsp;video news</p>', 'general', '', '17:30:00', '2017-10-29', '1509277949.mp4', 0, 5),
(150, '<p>&nbsp;xdszdas</p>', 'personal', '40', '19:30:00', '2017-10-29', '', 0, 5),
(151, '<p>testing</p>', 'general', '', '23:00:00', '2017-10-30', '', 0, 25),
(152, '<p>ahahahahahahahahah</p>\r\n<p>ahakakakakaka</p>\r\n<p>dlkfslf gfsala;,.v,jf.ds.d dsz</p>', 'general', '', '23:00:00', '2017-10-30', '1509393392.png', 0, 25),
(153, '<p>&nbsp;ok</p>', 'general', '', '23:00:00', '2017-10-30', '1509393439.png', 0, 25),
(154, '<p>jdjdjdjdjdlkllsllssls</p>', 'general', '', '23:00:00', '2017-10-30', '1509393555.png', 0, 25),
(155, '<p>class b messages</p>', 'personal', '43', '23:15:00', '2017-10-30', '', 0, 25),
(156, '<p>&nbsp;adasd</p>', 'general', '', '16:45:00', '2017-10-31', '', 0, 5),
(157, '<p>&nbsp;sadasdas</p>', 'general', '', '17:15:00', '2017-10-31', '', 0, 5),
(158, '<p>&nbsp;dasdasdasdas</p>', 'general', '', '17:15:00', '2017-10-31', '', 0, 5),
(159, '<p>&nbsp;dasd</p>', 'general', '', '18:00:00', '2017-10-31', '', 0, 5),
(160, '<p>&nbsp;dasd</p>', 'general', '', '18:00:00', '2017-10-31', '', 0, 5),
(161, '<p>&nbsp;dsaddasd</p>', 'general', '', '18:00:00', '2017-10-31', '', 0, 5),
(162, '<p>&nbsp;</p>\r\n<p>&nbsp;asdas</p>', 'general', '', '18:45:00', '2017-10-31', '', 0, 5),
(163, '<p>&nbsp;sadas</p>', 'general', '', '18:45:00', '2017-10-31', '', 0, 5),
(164, '<p>&nbsp;asds</p>', 'general', '', '18:45:00', '2017-10-31', '', 0, 5),
(165, '', 'general', '', '18:45:00', '2017-10-31', '1509455603.jpg', 0, 5),
(166, '', 'general', '', '17:30:00', '2017-10-31', '1509460028.png', 0, 23),
(167, '<p>oooooooooooooohhhhhhhhhhhhhhhhhh</p>', 'general', '', '17:30:00', '2017-10-31', '', 0, 23),
(168, '<p>&nbsp;www</p>', 'general', '', '18:30:00', '2017-11-01', '1509540511.jpg', 0, 5),
(169, '<p>&nbsp;www</p>', 'general', '', '18:30:00', '2017-11-01', '1509540511.jpg', 0, 5),
(170, '<p>&nbsp;dsasd</p>', 'general', '', '18:30:00', '2017-11-01', '', 0, 5),
(171, '<p>&nbsp;dsasd</p>', 'general', '', '18:30:00', '2017-11-01', '', 0, 5),
(172, '<p>&nbsp;dasdasdsad</p>', 'general', '', '18:30:00', '2017-11-01', '', 0, 5),
(173, '<p>gaurav</p>', 'general', '', '18:30:00', '2017-11-01', '', 0, 5),
(174, '<p>gaurav</p>', 'general', '', '19:00:00', '2017-11-01', '', 0, 5),
(175, '<p>final test</p>', 'general', '', '20:00:00', '2017-11-01', '', 0, 5),
(176, '<p>sxaDS</p>', 'general', '', '20:00:00', '2017-11-01', '', 0, 5),
(177, '<p>&nbsp;HELLO NEWS</p>', 'general', '', '20:00:00', '2017-11-01', '', 0, 5),
(178, '<p>&nbsp;sssasdassa</p>', 'general', '', '02:15:00', '2017-11-01', '', 0, 5),
(179, '<p>&nbsp;sdsadad</p>', 'general', '', '02:15:00', '2017-11-01', '', 0, 5),
(180, '<p>&nbsp;hellloooo</p>', 'general', '', '02:15:00', '2017-11-01', '', 0, 5),
(181, '<p>&nbsp;hellooo</p>', 'general', '', '02:15:00', '2017-11-01', '1509568421.jpg', 0, 5),
(183, '', 'general', '', '02:15:00', '2017-11-01', '1509568480.jpg', 0, 5),
(184, '<p>&nbsp;This is for testing</p>', 'personal', '40', '19:15:00', '2017-11-02', '1509629533.jpg', 0, 5),
(185, '<p>&nbsp;notification testing</p>', 'personal', '40', '19:15:00', '2017-11-02', '', 0, 5),
(186, '<p>&nbsp;hiiiiii...this is demo</p>', 'personal', '40', '19:30:00', '2017-11-02', '', 0, 5),
(187, '<p>&nbsp;news evening</p>', 'personal', '40', '20:00:00', '2017-11-02', '1509632331.jpg', 0, 5),
(188, '<p>&nbsp;this is latest news</p>', 'personal', '40', '13:00:00', '2017-11-03', '', 0, 5),
(189, '<p>&nbsp;general news test</p>', 'general', '', '13:00:00', '2017-11-03', '', 0, 5),
(190, '<p>&nbsp;this is for testing----general</p>', 'general', '', '13:00:00', '2017-11-03', '', 0, 5),
(191, '<p>&nbsp;last test</p>', 'general', '', '13:15:00', '2017-11-03', '', 0, 5),
(192, '<p>&nbsp;this is test for type</p>', 'general', '', '13:30:00', '2017-11-03', '', 0, 5),
(193, '<p>&nbsp;chck again</p>', 'general', '', '13:45:00', '2017-11-03', '', 0, 5),
(194, '<p>&nbsp;hiiiiiiii</p>', 'personal', '40', '13:45:00', '2017-11-03', '', 0, 5),
(195, '<p>&nbsp;this is latest</p>', 'personal', '40', '13:45:00', '2017-11-03', '', 0, 5),
(196, '<p>&nbsp;class A</p>', 'personal', '40', '13:45:00', '2017-11-03', '', 0, 5),
(197, '<p>&nbsp;this is personal</p>', 'personal', '40', '15:00:00', '2017-11-03', '1509700919.jpg', 0, 5),
(198, '<p>&nbsp;update news</p>', 'personal', '40', '15:00:00', '2017-11-03', '', 0, 5),
(199, '<p>&nbsp;againnnnnnn</p>', 'personal', '40', '15:00:00', '2017-11-03', '', 0, 5),
(200, '<p>&nbsp;lastttt</p>', 'personal', '40', '15:00:00', '2017-11-03', '', 0, 5),
(201, '<p>&nbsp;general news</p>', 'general', '', '15:15:00', '2017-11-03', '', 0, 5),
(202, '', 'general', '', '16:00:00', '2017-11-03', '1509705041.jpg', 0, 5),
(203, '', 'general', '', '16:15:00', '2017-11-03', '1509705300.jpg', 0, 5),
(205, '<p>&nbsp;hiiii</p>', 'general', '', '16:15:00', '2017-11-03', '1509705565.jpg', 0, 5),
(206, '<p>&nbsp;news 03 nov . hurrey</p>', 'general', '', '16:45:00', '2017-11-03', '1509707062.jpg', 0, 5),
(207, '<p>&nbsp;Testing&nbsp;</p>', 'general', '', '11:45:00', '2017-11-07', '', 0, 24),
(208, '<p>&nbsp;hello</p>', 'general', '', '01:00:00', '2017-11-07', '', 0, 18),
(209, '<p>&nbsp;hello 2</p>', 'general', '', '01:00:00', '2017-11-07', '', 0, 18),
(210, '<p>&nbsp;Hello news</p>', 'general', '', '16:00:00', '2017-11-08', '', 0, 5),
(211, '', 'general', '', '18:30:00', '2017-11-08', '1510145720.mp4', 0, 26),
(212, '<p>&nbsp;hiii this is news</p>', 'general', '', '18:45:00', '2017-11-08', '1510146516.jpg', 0, 26),
(213, '', 'general', '', '18:45:00', '2017-11-08', '1510146679.jpg', 0, 26),
(214, '', 'general', '', '18:45:00', '2017-11-08', '1510146749.png', 0, 26),
(215, '', 'general', '', '19:00:00', '2017-11-08', '1510147047.jpg', 0, 26),
(216, '<p>&nbsp;My newsss</p>', 'general', '', '19:00:00', '2017-11-08', '1510147141.jpg', 0, 26),
(217, '<p>&nbsp;jkfhkjdhgfjkd</p>', 'general', '', '19:00:00', '2017-11-08', '', 0, 26),
(218, '<p>sample message here</p>', 'general', '', '20:00:00', '2017-11-08', '', 0, 24),
(219, '', 'general', '', '18:30:00', '2017-11-08', '1510154287.jpg', 0, 24),
(220, '<p>woow message</p>', 'general', '', '18:30:00', '2017-11-08', '1510154533.png', 0, 24),
(221, '', 'general', '', '18:30:00', '2017-11-08', '1510154594.mp4', 0, 24),
(222, '<p>&nbsp;&nbsp; bbb</p>', 'personal', '28', '18:30:00', '2017-11-08', '', 0, 24),
(223, '<p>jjjjj</p>', 'general', '', '18:45:00', '2017-11-08', '1510155808.png', 0, 24),
(224, '', 'personal', '28', '18:45:00', '2017-11-08', '1510155869.png', 0, 24),
(225, '<p><img src="http://www.ssappsnwebs.com/spass/admin2/assets/fckeditor/editor/images/smiley/msn/sad_smile.gif" alt="" /></p>', 'general', '', '19:00:00', '2017-11-08', '', 0, 24),
(226, '<p>25th November is graduation day for top class children, all parents advised to attend</p>', 'general', '', '08:00:00', '2017-11-25', '1510159811.PNG', 0, 29),
(227, '<p>Transport is free for all children who have complete fees effective today :)</p>', 'general', '', '20:00:00', '2017-11-08', '', 0, 29),
(228, '<p>Term closes on 1st Dec, please come collect your child with valid identification</p>', 'general', '', '20:00:00', '2017-11-08', '', 0, 27),
(229, '<p>we are going yellow, excited!</p>', 'general', '', '20:00:00', '2017-11-08', '1510160391.png', 0, 27),
(230, '<p>super excited to be on spass......watch out for daily updates !!</p>\r\n<p>&nbsp;</p>', 'general', '', '20:15:00', '2017-11-08', '1510160618.jpg', 0, 28),
(231, '<p>&nbsp;Spass rocks, happy to be here????????</p>', 'general', '', '20:15:00', '2017-11-08', '', 0, 29),
(232, '<p>gayaza video shoot of the year</p>', 'general', '', '20:15:00', '2017-11-08', '', 0, 27),
(233, '<p>&nbsp;check notification</p>', 'general', '', '13:45:00', '2017-11-09', '', 0, 26),
(234, '<p>&nbsp;newsss</p>', 'general', '', '14:00:00', '2017-11-09', '1510215642.jpg', 0, 26),
(235, '<p>&nbsp;hello&nbsp;</p>', 'general', '', '00:00:00', '2017-11-09', '1510219012.gif', 0, 5),
(236, '<p>&nbsp;hello gif</p>', 'general', '', '15:00:00', '2017-11-09', '', 0, 26),
(237, '', 'general', '', '15:00:00', '2017-11-09', '1510219531.gif', 0, 26),
(238, '<p>&nbsp;my news</p>', 'general', '', '17:45:00', '2017-11-09', '1510228909.gif', 0, 26),
(241, '<ul>\r\n    <li><span style="background-color: yellow;"><strong>&nbsp;<u><span style="font-family: Verdana;">hiiiii<img src="http://www.ssappsnwebs.com/spass/admin2/assets/fckeditor/editor/images/smiley/msn/sad_smile.gif" alt="" /></span></u></strong></span></li>\r\n</ul>', 'general', '', '13:30:00', '2017-11-10', '', 0, 26),
(242, '<p>&nbsp;<span style="color: rgb(255, 0, 0);">colored</span></p>', 'general', '', '13:30:00', '2017-11-10', '', 0, 26),
(243, '<p>&nbsp;Hi this is<span style="color: rgb(51, 153, 102);"><em><strong> <span style="font-size: larger;">latest </span><span style="font-size: larger;">news</span></strong></em></span><em><strong><span style="font-size: larger;">..<img src="http://www.ssappsnwebs.com/spass/admin2/assets/fckeditor/editor/images/smiley/msn/regular_smile.gif" alt="" /></span></strong></em></p>\r\n<p><span style="color: rgb(153, 51, 102);">Pls remember</span></p>', 'general', '', '16:30:00', '2017-11-10', '', 0, 26),
(244, '<p>Good <span style="color: rgb(255, 0, 0);">news</span>, today is <span style="background-color: rgb(255, 102, 0);">friday </span>:) <img src="http://www.ssappsnwebs.com/spass/admin2/assets/fckeditor/editor/images/smiley/msn/thumbs_up.gif" alt="" /></p>', 'general', '', '15:00:00', '2017-11-10', '', 0, 29),
(245, '<h3 style="color: red;">&nbsp;This Is good Way For <span style="font-family: &quot;Times New Roman&quot;;"><span style="background-color: rgb(255, 255, 255);"><span style="color: rgb(255, 153, 0);"><span style="font-size: medium;">Testing<img src="http://www.ssappsnwebs.com/spass/admin2/assets/fckeditor/editor/images/smiley/msn/devil_smile.gif" alt="" /></span></span></span></span></h3>', 'general', '', '17:30:00', '2017-11-10', '', 0, 26),
(248, '<p>good frinday updates</p>', 'general', '', '15:15:00', '2017-11-10', '', 0, 29),
(260, '<p>&nbsp;asasasa</p>', 'general', '', '18:15:00', '2017-11-10', '', 0, 26),
(261, '<p>&nbsp;fghfghfgh</p>', 'general', '', '18:15:00', '2017-11-10', '', 0, 26),
(262, '<p>&nbsp;fggfh</p>', 'general', '', '18:15:00', '2017-11-10', '', 0, 26),
(263, '<p>&nbsp;jjhh</p>', 'general', '', '18:30:00', '2017-11-10', '', 0, 26),
(301, '<p>Lovely weekend to you all, please ensure your child complets the weekdend assisgnment under your supervision <img src="http://www.ssappsnwebs.com/spass/admin2/assets/fckeditor/editor/images/smiley/msn/regular_smile.gif" alt="" /></p>', 'general', '', '07:30:00', '2017-11-11', '', 0, 29),
(302, '<p>We are commissionming our new library block today, all parents advised to come witness</p>', 'general', '', '07:45:00', '2017-11-11', '1510374788.jpg', 0, 27),
(303, '<p>School aprenticeship classes start on Monday for all S6 only 50K each</p>', 'general', '', '08:15:00', '2017-11-11', '', 0, 27),
(304, '<p>&nbsp;fgfdkjvnkjfvn</p>', 'general', '', '13:30:00', '2017-11-11', '', 0, 26),
(305, '<p>&nbsp;ss web and app</p>', 'general', '', '13:30:00', '2017-11-11', '', 0, 26),
(306, '<p>&nbsp;fdesfdsfds</p>', 'general', '', '13:30:00', '2017-11-11', '1510386568.png', 0, 26),
(307, '<p>&nbsp;dfgtfyt</p>', 'general', '', '13:30:00', '2017-11-16', '', 0, 26);

-- --------------------------------------------------------

--
-- Table structure for table `parent_info`
--

CREATE TABLE IF NOT EXISTS `parent_info` (
  `parent_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_name` varchar(500) NOT NULL,
  `parent_mailid` varchar(500) NOT NULL,
  `parent_school` varchar(4000) NOT NULL,
  `parent_contact` varchar(50) NOT NULL,
  `parent_otp` varchar(11) NOT NULL,
  `parent_status` int(11) NOT NULL,
  `school_id` int(11) NOT NULL,
  PRIMARY KEY (`parent_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `parent_info`
--

INSERT INTO `parent_info` (`parent_id`, `parent_name`, `parent_mailid`, `parent_school`, `parent_contact`, `parent_otp`, `parent_status`, `school_id`) VALUES
(17, 'Shweta Kumar', 'shwetakumar@gmail.com', '14', '7869234669', '', 1, 14),
(18, 'Kaushik Kumar', 'kaushikkumar@gmail.com', '14', '7777777777', '', 1, 14),
(19, 'Sohil khan', 'sohilkhan@gmail.com', '14', '1234567891', '', 1, 14),
(20, 'Aparents1', 'Aparents1@gmail.com', '20', '8698350901', '', 1, 20),
(21, 'Aparents2', 'Aparents2@gmail.com', '20', '8698350901', '', 1, 20),
(22, 'B1', 'B1@gmail.com', '21', '7854788574', '', 1, 21),
(23, 'B2', 'B2@gmail.com', '21', '8565585555', '', 1, 21),
(24, 'gaurav mourya', 'gg@g.com', '37', '9898989898', '', 1, 37),
(25, 'shweta', 's@s.com', '40', '1111111111', '', 1, 40),
(26, 'Ganja Planter', 'gg@ff.net', '41', '0789123431', '', 1, 41),
(27, 'Amittabh Khatam', 'a@a.com', '41', '0790999000', '', 1, 41),
(28, 'Ox Danger', 'ss@ss.net', '41', '0798789456', '', 1, 41),
(29, 'demo', 'demo@g.c', '37', '2312321321312', '', 1, 37),
(32, 'p', 'p@123.com', '33', '0123456789', '', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `parent_to_class`
--

CREATE TABLE IF NOT EXISTS `parent_to_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `school_id` int(11) NOT NULL,
  `class_ids` varchar(100) NOT NULL,
  `date_modified` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=936 ;

--
-- Dumping data for table `parent_to_class`
--

INSERT INTO `parent_to_class` (`id`, `parent_id`, `school_id`, `class_ids`, `date_modified`) VALUES
(2, 3, 14, '16', '2017-09-22 10:40:40'),
(3, 24, 14, '16,17', '2017-09-25 07:58:41'),
(26, 12, 14, '16,17', '2017-09-27 12:11:32'),
(33, 12, 14, '16,17', '2017-09-28 06:45:42'),
(34, 12, 16, '18,19', '2017-09-28 06:45:42'),
(36, 12, 14, '17', '2017-09-28 06:56:48'),
(37, 12, 16, '18,19', '2017-09-28 06:56:48'),
(40, 12, 14, '16,17', '2017-09-28 07:22:26'),
(41, 12, 16, '18,19', '2017-09-28 07:22:26'),
(42, 12, 14, '16,17', '2017-09-28 07:28:21'),
(43, 12, 16, '18,19', '2017-09-28 07:28:21'),
(44, 19, 14, '16,17', '2017-09-28 07:35:32'),
(45, 19, 16, '18,19', '2017-09-28 07:35:32'),
(58, 24, 16, '19', '2017-09-28 12:20:13'),
(59, 24, 14, '16', '2017-09-28 01:14:05'),
(60, 24, 16, '18', '2017-09-28 01:14:05'),
(71, 26, 14, '16', '2017-09-28 04:46:52'),
(72, 26, 16, '18', '2017-09-28 04:46:52'),
(78, 28, 14, '16,17', '2017-09-30 06:29:28'),
(79, 28, 16, '18,19', '2017-09-30 06:29:28'),
(80, 28, 16, '18,19', '2017-09-30 06:30:58'),
(95, 32, 14, '16,17', '2017-10-03 10:47:06'),
(96, 32, 16, '18,19', '2017-10-03 10:47:06'),
(97, 32, 14, '16,17', '2017-10-03 11:39:19'),
(98, 32, 16, '18', '2017-10-03 11:39:19'),
(99, 32, 14, '16,17', '2017-10-03 11:39:31'),
(100, 32, 16, '18', '2017-10-03 11:39:31'),
(108, 32, 14, '16,17', '2017-10-03 02:08:41'),
(109, 32, 16, '19', '2017-10-03 02:08:41'),
(112, 32, 14, '16', '2017-10-04 06:44:57'),
(113, 32, 16, '19', '2017-10-04 06:44:57'),
(114, 32, 14, '16', '2017-10-04 07:28:12'),
(115, 32, 16, '18,19', '2017-10-04 07:28:12'),
(120, 32, 14, '16,17', '2017-10-04 08:43:05'),
(121, 32, 16, '18', '2017-10-04 08:43:05'),
(122, 32, 16, '18,19', '2017-10-04 09:22:59'),
(126, 32, 20, '20,21', '2017-10-04 11:07:48'),
(127, 32, 16, '18,19', '2017-10-04 11:07:48'),
(128, 32, 14, '16,17', '2017-10-04 11:21:26'),
(129, 32, 16, '18,19', '2017-10-04 11:21:26'),
(130, 32, 20, '20,21', '2017-10-04 11:26:20'),
(131, 32, 16, '18,19', '2017-10-04 11:26:20'),
(132, 32, 20, '20,21', '2017-10-04 11:31:59'),
(133, 32, 16, '18,19', '2017-10-04 11:31:59'),
(134, 32, 20, '20,21', '2017-10-04 11:39:36'),
(135, 32, 16, '18,19', '2017-10-04 11:39:36'),
(136, 32, 20, '20,21', '2017-10-04 11:54:43'),
(137, 32, 16, '18,19', '2017-10-04 11:54:43'),
(138, 32, 20, '20,21', '2017-10-04 12:00:30'),
(139, 32, 16, '18,19', '2017-10-04 12:00:30'),
(142, 41, 14, '16', '2017-10-04 01:41:06'),
(143, 32, 14, '16,17', '2017-10-05 05:57:48'),
(144, 32, 14, '16,17', '2017-10-05 06:47:09'),
(145, 32, 14, '16,17', '2017-10-05 06:56:40'),
(146, 32, 16, '18', '2017-10-05 06:56:40'),
(147, 32, 14, '16', '2017-10-05 06:58:21'),
(148, 32, 14, '16,17', '2017-10-05 01:25:33'),
(149, 32, 14, '16,17', '2017-10-05 01:54:11'),
(150, 32, 14, '17', '2017-10-05 02:39:41'),
(151, 32, 14, '16,17', '2017-10-06 07:57:56'),
(152, 32, 14, '17', '2017-10-06 08:58:01'),
(160, 55, 14, '16,17', '2017-10-06 12:39:52'),
(161, 55, 14, '16,17', '2017-10-06 01:28:52'),
(167, 61, 16, '18,19', '2017-10-06 02:15:48'),
(173, 55, 0, '', '2017-10-07 05:42:56'),
(230, 68, 14, '16,17', '2017-10-07 02:53:31'),
(231, 68, 0, '', '2017-10-07 02:53:31'),
(232, 68, 16, '18,19', '2017-10-07 02:53:31'),
(330, 77, 20, '', '2017-10-08 01:46:30'),
(331, 77, 14, '', '2017-10-08 01:46:30'),
(332, 77, 15, '', '2017-10-08 01:46:30'),
(333, 77, 22, '', '2017-10-08 01:46:30'),
(334, 77, 21, '', '2017-10-08 01:46:30'),
(335, 77, 16, '', '2017-10-08 01:46:30'),
(350, 78, 14, '', '2017-10-08 03:15:54'),
(351, 78, 15, '', '2017-10-08 03:15:54'),
(357, 49, 0, '', '2017-10-10 06:03:40'),
(358, 49, 16, '18,19', '2017-10-10 06:03:40'),
(365, 83, 16, '19', '2017-10-11 08:56:59'),
(373, 84, 16, '18,19', '2017-10-12 08:19:09'),
(378, 85, 14, '24,17', '2017-10-13 02:00:35'),
(379, 85, 15, '', '2017-10-13 02:00:35'),
(381, 86, 14, '24,25', '2017-10-14 09:38:50'),
(382, 86, 15, '', '2017-10-14 09:38:50'),
(383, 80, 14, '17,25', '2017-10-14 09:52:16'),
(386, 87, 23, '26', '2017-10-14 10:25:08'),
(388, 88, 14, '24,25', '2017-10-14 01:41:28'),
(389, 88, 23, '26', '2017-10-14 01:41:28'),
(434, 2, 23, '26', '2017-10-15 07:23:58'),
(435, 2, 20, '20,21', '2017-10-15 07:23:58'),
(436, 2, 14, '24,17,25', '2017-10-15 07:23:58'),
(437, 2, 21, '22,23', '2017-10-15 07:23:58'),
(438, 2, 18, '', '2017-10-15 07:23:58'),
(439, 2, 17, '', '2017-10-15 07:23:58'),
(440, 2, 15, '', '2017-10-15 07:23:58'),
(441, 2, 16, '18,19', '2017-10-15 07:23:58'),
(442, 2, 22, '', '2017-10-15 07:23:58'),
(443, 4, 14, '24,17,25', '2017-10-17 07:53:47'),
(444, 7, 24, '27,28,29', '2017-10-17 08:19:59'),
(459, 9, 16, '18', '2017-10-17 08:06:23'),
(463, 10, 26, '', '2017-10-23 07:06:06'),
(464, 10, 25, '', '2017-10-23 07:06:06'),
(467, 13, 24, '27', '2017-10-23 07:15:52'),
(470, 14, 14, '24', '2017-10-23 07:18:58'),
(476, 15, 14, '24,17,25', '2017-10-23 07:27:18'),
(479, 18, 14, '', '2017-10-23 08:08:13'),
(480, 18, 24, '27', '2017-10-23 08:08:13'),
(481, 20, 14, '24', '2017-10-23 08:17:42'),
(482, 20, 24, '', '2017-10-23 08:17:42'),
(483, 21, 14, '24', '2017-10-23 08:39:41'),
(484, 21, 24, '', '2017-10-23 08:39:41'),
(486, 27, 24, '27', '2017-10-23 10:01:31'),
(487, 30, 14, '', '2017-10-23 12:29:34'),
(488, 11, 14, '', '2017-10-23 01:12:33'),
(501, 33, 40, '33', '2017-10-24 02:15:34'),
(519, 35, 37, '30', '2017-10-25 11:53:49'),
(537, 1, 41, '36,37', '2017-10-27 02:44:04'),
(538, 1, 37, '30,31,32', '2017-10-27 02:44:04'),
(539, 1, 40, '', '2017-10-27 02:44:04'),
(543, 38, 37, '30,31,32', '2017-10-28 08:33:28'),
(544, 38, 42, '38,39', '2017-10-28 08:33:28'),
(548, 39, 37, '31,32', '2017-10-28 12:38:12'),
(549, 39, 42, '38,39', '2017-10-28 12:38:12'),
(562, 36, 5, '40,41', '2017-10-29 01:40:51'),
(563, 8, 5, '', '2017-10-29 03:23:51'),
(564, 8, 15, '', '2017-10-29 03:23:51'),
(565, 42, 5, '', '2017-10-29 03:31:22'),
(566, 42, 15, '', '2017-10-29 03:31:22'),
(569, 43, 5, '40,41', '2017-10-30 07:44:06'),
(599, 50, 20, '20,21', '2017-10-31 10:12:09'),
(600, 50, 25, '42', '2017-10-31 10:12:09'),
(601, 50, 24, '27', '2017-10-31 10:12:09'),
(602, 51, 20, '', '2017-10-31 01:04:10'),
(603, 51, 25, '', '2017-10-31 01:04:10'),
(629, 52, 23, '', '2017-11-01 12:29:20'),
(630, 52, 5, '40,41', '2017-11-01 12:29:20'),
(634, 53, 5, '40,41', '2017-11-01 01:26:52'),
(644, 31, 25, '', '2017-11-02 07:48:13'),
(645, 31, 5, '40,41', '2017-11-02 07:48:13'),
(646, 31, 24, '', '2017-11-02 07:48:13'),
(647, 31, 22, '', '2017-11-02 07:48:13'),
(648, 31, 19, '', '2017-11-02 07:48:13'),
(696, 58, 23, '', '2017-11-02 01:25:41'),
(697, 58, 5, '40,41', '2017-11-02 01:25:41'),
(714, 59, 5, '40,41', '2017-11-02 03:21:37'),
(731, 60, 18, '', '2017-11-02 06:30:27'),
(732, 60, 5, '40,41', '2017-11-02 06:30:27'),
(733, 60, 24, '', '2017-11-02 06:30:27'),
(734, 6, 23, '', '2017-11-02 07:18:27'),
(735, 6, 5, '40,41', '2017-11-02 07:18:27'),
(755, 62, 5, '40,41', '2017-11-03 07:04:23'),
(756, 63, 5, '40,41', '2017-11-03 07:19:51'),
(757, 37, 5, '40,41', '2017-11-03 07:27:57'),
(758, 64, 5, '40,41', '2017-11-03 07:35:33'),
(762, 66, 5, '40', '2017-11-03 07:46:59'),
(763, 65, 5, '40', '2017-11-03 07:48:02'),
(764, 67, 5, '40', '2017-11-03 07:52:34'),
(767, 69, 5, '40,41', '2017-11-03 10:22:02'),
(777, 72, 25, '', '2017-11-05 05:54:33'),
(778, 73, 23, '', '2017-11-06 05:10:55'),
(779, 73, 25, '', '2017-11-06 05:10:55'),
(786, 74, 26, '', '2017-11-08 01:03:33'),
(788, 75, 26, '', '2017-11-08 01:28:43'),
(789, 76, 18, '', '2017-11-08 01:50:39'),
(790, 76, 25, '42,43', '2017-11-08 01:50:39'),
(791, 76, 24, '27,28', '2017-11-08 01:50:39'),
(792, 76, 22, '', '2017-11-08 01:50:39'),
(824, 45, 28, '', '2017-11-09 07:18:25'),
(825, 45, 25, '42,43', '2017-11-09 07:18:25'),
(826, 45, 26, '', '2017-11-09 07:18:25'),
(827, 45, 29, '', '2017-11-09 07:18:25'),
(830, 79, 26, '', '2017-11-09 08:13:10'),
(831, 81, 26, '', '2017-11-09 12:12:51'),
(833, 82, 26, '', '2017-11-09 01:04:36'),
(845, 47, 26, '', '2017-11-10 06:37:58'),
(851, 70, 28, '', '2017-11-10 10:26:51'),
(852, 70, 27, '', '2017-11-10 10:26:51'),
(853, 70, 29, '', '2017-11-10 10:26:51'),
(856, 54, 26, '', '2017-11-10 11:11:47'),
(861, 44, 26, '', '2017-11-10 01:56:04'),
(866, 89, 27, '', '2017-11-11 03:24:42'),
(867, 89, 29, '', '2017-11-11 03:24:42'),
(869, 56, 27, '', '2017-11-11 04:34:22'),
(870, 56, 25, '', '2017-11-11 04:34:22'),
(871, 56, 29, '', '2017-11-11 04:34:22'),
(872, 93, 28, '', '2017-11-11 06:49:48'),
(877, 57, 28, '', '2017-11-11 08:28:52'),
(878, 57, 25, '', '2017-11-11 08:28:52'),
(879, 57, 26, '', '2017-11-11 08:28:52'),
(882, 94, 27, '', '2017-11-11 11:10:21'),
(897, 95, 27, '44,45', '2017-11-14 12:23:19'),
(898, 95, 26, '', '2017-11-14 12:23:19'),
(899, 95, 29, '', '2017-11-14 12:23:19'),
(912, 71, 29, '', '2017-11-15 11:08:58'),
(915, 97, 26, '', '2017-11-16 07:55:38'),
(918, 99, 28, '', '2017-11-16 05:22:05'),
(919, 99, 27, '', '2017-11-16 05:22:05'),
(920, 99, 25, '', '2017-11-16 05:22:05'),
(921, 99, 26, '', '2017-11-16 05:22:05'),
(922, 99, 29, '', '2017-11-16 05:22:05'),
(929, 34, 25, '42,43', '2017-11-29 10:40:44'),
(935, 101, 28, '', '2017-12-05 08:50:50');

-- --------------------------------------------------------

--
-- Table structure for table `school_info`
--

CREATE TABLE IF NOT EXISTS `school_info` (
  `school_id` int(11) NOT NULL AUTO_INCREMENT,
  `school_password` varchar(500) NOT NULL,
  `school_name` varchar(4000) NOT NULL,
  `school_address` varchar(4000) NOT NULL,
  `school_country` varchar(255) NOT NULL,
  `school_logo` varchar(4000) NOT NULL,
  `school_banner` varchar(4000) NOT NULL,
  `school_website` varchar(4000) NOT NULL,
  `school_timming` varchar(4000) NOT NULL,
  `school_contact` varchar(100) NOT NULL,
  `alt_school_contact` varchar(100) NOT NULL,
  `school_status` varchar(50) NOT NULL,
  `school_mailid` varchar(50) NOT NULL,
  `schoolId` varchar(100) NOT NULL,
  `school_username` varchar(50) NOT NULL,
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=34 ;

--
-- Dumping data for table `school_info`
--

INSERT INTO `school_info` (`school_id`, `school_password`, `school_name`, `school_address`, `school_country`, `school_logo`, `school_banner`, `school_website`, `school_timming`, `school_contact`, `alt_school_contact`, `school_status`, `school_mailid`, `schoolId`, `school_username`) VALUES
(25, 'kk', 'kk school', 'kk', 'Uganda', '/assets/school/thumb/150939225844284.png', '', 'kk.com', '111', '1232434', '1232434', '1', 'kk@kk.com', 'kkk', 'kk'),
(26, '123', 'Gaurav Mourya', 'indore', 'India', '/assets/school/thumb/151014554393630.png', '', 's.s.s', '4-5', '8878765084', '8878765084', '1', 'gauravmouryaweb@gmail.com', 'gaurav123', 'gaurav'),
(27, 'ghs', 'Gayaza High School', 'Kampala', 'Uganda', '/assets/school/thumb/151015923544989.png', '', 'ghs.com', '24 Hours', '01231234555', '321456456', '1', 'ghs@ghs.com', 'KLA002', 'ghs'),
(28, 'mss', 'Mukono Secondary School', 'mss, kamplaka', 'Uganda', '/assets/school/thumb/151015961216315.png', '', 'none', '1-6PM', '32145698', '3698524478', '1', 'mss@gmail.com', 'LLOO1', 'mss'),
(29, 'gns', 'Gayaza Nursery School', 'kla', 'Uganda', '', '', '', '24 Hours', '65498712', '569874112', '1', 'gns@gns.com', 'GHKK', 'gns'),
(30, 'vishal', 'vishal', 'vishal', 'India', '/assets/school/thumb/151394179070178.png', '', 'www.abc.com', '3-4', '999999999999', '999999999999', '1', 'v@v.vom', '2000', 'vishal'),
(31, 'vishal', 'vishal', 'vishal', 'India', '/assets/school/thumb/151394181882373.png', '', 'www.abc.com', '3-4', '999999999999', '999999999999', '1', 'vv@v.vom', '2000', 'vishals'),
(32, 'vishal', 'vishal', 'vishal', 'India', '/assets/school/thumb/151394185614422.png', '', 'www.abc.com', '3-4', '999999999999', '999999999999', '1', 'vv@v.com', '2000', 'www12'),
(33, 'wq', 'wq', 'wq', 'India', '/assets/school/thumb/151394198957549.png', '', 'www.ccccc.com', '3-4', '999999999999', '999999999999', '1', 'wq@wq.com', '3000', 'wq');

-- --------------------------------------------------------

--
-- Table structure for table `staff_directory`
--

CREATE TABLE IF NOT EXISTS `staff_directory` (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT,
  `staff_school_username` varchar(4000) NOT NULL,
  `staff_name` varchar(500) NOT NULL,
  `staff_number` varchar(250) NOT NULL,
  `staff_status` int(11) NOT NULL,
  `school_id` int(11) NOT NULL,
  `designation` varchar(150) NOT NULL,
  `email` varchar(250) NOT NULL,
  PRIMARY KEY (`staff_id`),
  UNIQUE KEY `staff_name` (`staff_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `staff_directory`
--

INSERT INTO `staff_directory` (`staff_id`, `staff_school_username`, `staff_name`, `staff_number`, `staff_status`, `school_id`, `designation`, `email`) VALUES
(1, 'St.Marry', 'sir john', '7896543211', 1, 16, 'maths teacher', 'john@gmail.com'),
(2, 'St.Marry', 'sir.marsh', '8899665544', 1, 16, 'English teacher', 'marsh12@gmail.com'),
(3, '', 'miss.julee', '78888545454', 1, 16, 'science Teacher', 'julee@gmail.com'),
(4, '', 'A1', '7788996655', 1, 20, 'science Teacher', 'A1@gmail.com'),
(5, '', 'A2', '7418529633', 1, 20, 'History Teacher', 'A2@gmail.com'),
(6, '', 'Allan Smith', '789654', 1, 14, 'Tr', 'ss@ss.net'),
(7, '', 'Rohit', '5555555555', 1, 37, 'class teacher', 'r@r.com'),
(8, '', 'rahul', '6666666666666', 1, 40, 'class head', 'rahul@gmail.com'),
(9, '', 'Tr Billy', '098767789098', 1, 41, 'Teacher', 'ww@ee.oo'),
(10, '', 'Ok Ok', '89078990722', 1, 41, 'Chef', 'cc@ee.oo');

-- --------------------------------------------------------

--
-- Table structure for table `stared`
--

CREATE TABLE IF NOT EXISTS `stared` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `news_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `stared`
--

INSERT INTO `stared` (`id`, `parent_id`, `news_id`, `status`) VALUES
(1, 10, 10, 1),
(2, 10, 10, 1),
(3, 10, 10, 1),
(4, 10, 10, 1),
(5, 10, 10, 1),
(6, 10, 10, 1),
(7, 10, 10, 1),
(8, 10, 10, 1),
(9, 10, 10, 1),
(10, 10, 10, 1);

-- --------------------------------------------------------

--
-- Table structure for table `starred_news`
--

CREATE TABLE IF NOT EXISTS `starred_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `news_id` int(11) NOT NULL,
  `givenBy` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `starred_news`
--

INSERT INTO `starred_news` (`id`, `news_id`, `givenBy`, `status`, `date`) VALUES
(1, 3, 12, 1, '0000-00-00 00:00:00'),
(2, 3, 20, 3, '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `stnd_info`
--

CREATE TABLE IF NOT EXISTS `stnd_info` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) NOT NULL,
  `school_id` int(11) NOT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=46 ;

--
-- Dumping data for table `stnd_info`
--

INSERT INTO `stnd_info` (`class_id`, `class_name`, `school_id`) VALUES
(24, 'Class 1', 14),
(17, '6th Standard', 14),
(18, '5th standard', 16),
(19, '7th standard', 16),
(20, 'class 2nd', 20),
(21, 'class 3rd', 20),
(22, 'class 10th', 21),
(23, 'class 9th', 21),
(25, 'Class 3', 14),
(26, 'class 2nd', 23),
(27, '1st standard', 24),
(28, '4th  standard', 24),
(29, '8th standard', 24),
(30, '4th standard', 37),
(31, '6th standard', 37),
(32, '8th standard', 37),
(33, '9th standard', 40),
(34, '2nd standards', 40),
(35, '3rd standard', 40),
(36, 'S1', 41),
(37, 'S2', 41),
(38, 'first class', 42),
(39, 'second class', 42),
(40, 'Class A', 5),
(41, 'Class B', 5),
(42, 'class a', 25),
(43, 'class b', 25),
(44, 'S1', 27),
(45, 'S2', 27);

-- --------------------------------------------------------

--
-- Table structure for table `test_users`
--

CREATE TABLE IF NOT EXISTS `test_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7313 ;

-- --------------------------------------------------------

--
-- Table structure for table `user_feedback`
--

CREATE TABLE IF NOT EXISTS `user_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `feedback` varchar(500) NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=54 ;

--
-- Dumping data for table `user_feedback`
--

INSERT INTO `user_feedback` (`id`, `user_id`, `feedback`, `date`) VALUES
(1, 12, 'ggfddd', '2017-09-27 03:37:06'),
(2, 12, 'gsgshsjskaksmsms', '2017-09-27 03:45:15'),
(3, 12, 'ggggg', '2017-09-27 03:45:26'),
(4, 12, 'fafaffa', '2017-09-27 03:45:52'),
(5, 12, 'rehan khan', '2017-09-27 03:48:48'),
(6, 12, 'its a. very nice appp', '2017-09-27 03:49:35'),
(7, 12, 'ggfddd', '2017-09-27 05:10:45'),
(8, 8, 'gsgsbsbsbsbsbs', '2017-09-27 06:26:14'),
(9, 20, 'ggfddd', '2017-09-28 01:10:51'),
(10, 8, 'kffogogog', '2017-09-28 04:14:27'),
(11, 8, 'kfkgkgkgkglg', '2017-09-28 04:17:02'),
(12, 8, 'kfkgkgkgkglgjfjjtk', '2017-09-28 04:17:09'),
(13, 8, 'gzbccmvmvm', '2017-09-28 04:17:46'),
(14, 8, 'gzbccmvmvm', '2017-09-28 04:19:30'),
(15, 8, 'jdjsjsksmww', '2017-09-28 04:22:36'),
(16, 8, 'jfkfjgkgk\n', '2017-09-28 04:25:05'),
(17, 26, 'kvkvovobkvo k kvk kvkvk kv km', '2017-09-28 09:47:50'),
(18, 20, 'ggfddd', '2017-09-30 04:52:16'),
(19, 27, 'Rehannnnnnnnnnnn', '2017-09-30 17:54:53'),
(20, 30, 'zjzsjksks', '2017-09-30 21:26:52'),
(21, 31, 'vjhngnhiCNNg g', '2017-10-03 01:07:00'),
(22, 31, 'hxhc cvv\n\n\n\n\nbbnb\n.\n..mm.bnnn......\n\n\n\n\n\n\n\nnnnnn\n\n\n', '2017-10-03 03:13:47'),
(23, 31, 'xhisjjs', '2017-10-03 03:15:34'),
(24, 31, 'ahsvshslhsljdljs', '2017-10-03 03:20:04'),
(25, 32, 'bdbdbxbxbxb', '2017-10-04 03:35:27'),
(26, 39, 'bsbsnsnsnsn', '2017-10-04 03:35:39'),
(27, 39, '', '2017-10-04 03:35:49'),
(28, 32, 'bdbdbxbxbxb', '2017-10-04 03:36:03'),
(29, 32, 'aaaaaaaaaaaaaaa', '2017-10-04 03:37:07'),
(30, 32, 'aaaaaaaaaaaaaa', '2017-10-04 03:37:10'),
(31, 32, 'aaaaaaaaaaaaab', '2017-10-04 03:37:14'),
(32, 39, 'hbbbbbbbbb\nbbbb', '2017-10-04 03:37:33'),
(33, 32, 'gvbbb', '2017-10-04 03:53:51'),
(34, 32, 'hfnjgmvlbmb', '2017-10-04 04:10:19'),
(35, 39, 'hfjffkkrktM.........dmrmrmrmrrmmrmrmrnrnrnrnrnrnrnrnrnrmr', '2017-10-04 04:12:11'),
(36, 32, 'whwweeeeeewwwew\nwwwww', '2017-10-04 04:33:38'),
(37, 32, 'wwwwwwwwwww\nwwww', '2017-10-04 04:40:49'),
(38, 32, 'wwwwwww\nweesssdd', '2017-10-04 05:01:47'),
(39, 6, '', '2017-10-06 04:38:57'),
(40, 6, 'ghjnn', '2017-10-06 04:39:01'),
(41, 57, 'jgkgkhkg', '2017-10-06 07:02:31'),
(42, 49, 'gdgd', '2017-10-07 00:24:11'),
(43, 20, 'ggfddd', '2017-10-07 00:59:16'),
(44, 78, 'test test', '2017-10-09 00:49:05'),
(45, 1, 'Wweeew\nWewwww\nWwwwww\n\n\n\n\n', '2017-10-15 00:15:17'),
(46, 9, 'boy', '2017-10-17 13:09:43'),
(47, 57, 'good ', '2017-11-02 02:27:43'),
(48, 67, 'this is nice', '2017-11-03 02:40:38'),
(49, 67, 'xgdbvjhfcjfjg', '2017-11-03 03:16:02'),
(50, 54, 'good working application ', '2017-11-10 04:14:18'),
(51, 54, 'dfsdf', '2017-11-23 23:55:00'),
(52, 54, 'thankx', '2017-11-23 23:56:22'),
(53, 54, 'dfsdf', '2017-12-13 05:44:30');

-- --------------------------------------------------------

--
-- Table structure for table `user_group`
--

CREATE TABLE IF NOT EXISTS `user_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user_mobile`
--

CREATE TABLE IF NOT EXISTS `user_mobile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(100) NOT NULL,
  `otp` varchar(20) NOT NULL,
  `device_no` varchar(100) NOT NULL,
  `status` int(11) NOT NULL,
  `regId` varchar(300) NOT NULL,
  `last_login` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=102 ;

--
-- Dumping data for table `user_mobile`
--

INSERT INTO `user_mobile` (`id`, `mobile`, `otp`, `device_no`, `status`, `regId`, `last_login`) VALUES
(1, '7999948463', '3569', '861598030213053', 1, 'frdUUVGHddU:APA91bH61-dnrYPmHe0Q6svXpOmXtuNtMWEEJmWKUWTw7VJCjnvGi1hsSVKS58SkHIAp-jjfdS2cBZkfl9PmgpRF6Mr4qOgo_DB3bEFRZN5rkT8hYpWMMoez-Wc6_p3KOV0Dt1emwQ1X', '30-10-2017 01-09-PM'),
(2, '8889821155', '3991', '862101032200837', 1, 'cKHc4yYVTko:APA91bEpEp9V7VjArQmI04RWYznHlY3S-SOEzo2iH0Uo_k_oCNm0P9vtcgVvcptyY8yAS00Xc13Nx1YWP26oc9a-PZWHFTNeYGjjTCE5BVift_8f6bYurRtL56QTGDNLLkxSBugSn1tm', ''),
(3, '1414141414', '5790', '861598030213053', 1, 'd4tUm5yTl-Y:APA91bF8NWRJDDW4Od5G4GtDESSp5n7dEExjMsr88fgYWawjnnVgGLCQBabOlCAOEQEile9fcnPglmfd3l8YHanjh6b1zJXgdUGlyJYOywi53-uqV4FRoBwx3Ou2nKc7SwvXQI_np-Ga', ''),
(4, '8698350901', '5355', '911440900500642', 1, 'deinEdne6ys:APA91bHwcMHiRO9b-DicqfQeye65jX65bzR3YXFkcy_8OYA8ds46ginLRfqfO5Gp1xz5uSIU5v-OmETuTShn1udOlcVnwJW28L5x9UBxcCL77JpxE98YsgId5yIYwj-6bfAF6V0V_RP0', ''),
(5, '4578963210', '9278', '911440900500642', 1, 'deinEdne6ys:APA91bHwcMHiRO9b-DicqfQeye65jX65bzR3YXFkcy_8OYA8ds46ginLRfqfO5Gp1xz5uSIU5v-OmETuTShn1udOlcVnwJW28L5x9UBxcCL77JpxE98YsgId5yIYwj-6bfAF6V0V_RP0', ''),
(6, '1478523690', '2761', '911440900500642', 1, 'fulAVoGhWfs:APA91bEjCMgVEM3Oef3fzWNcbkhralz21th59gyUzB6tnw5LhIJkdYRhkx59f6Pjvy6aaseV1bUU8bpFsi4xMXCoBQPIDQ1_N7cfUYSnsd441Qf9f86gQSJQGpeiBJpyGF8_VEf1oeGo', '03-11-2017 12-48-AM'),
(7, '7999948463', '8600', '911440900500642', 1, 'deinEdne6ys:APA91bHwcMHiRO9b-DicqfQeye65jX65bzR3YXFkcy_8OYA8ds46ginLRfqfO5Gp1xz5uSIU5v-OmETuTShn1udOlcVnwJW28L5x9UBxcCL77JpxE98YsgId5yIYwj-6bfAF6V0V_RP0', ''),
(8, '778123654', '2955', '353285067861849', 1, 'cWTzHo_Rz70:APA91bG1S3cicvGhUk-MfrNTorvxpSuJdijLU8Mt7ei2HLnuI46vVRYDgGhEXl_KQVBgpOxUuR8wgrR5pWP4Ym_gMTS0PtWelRBUwOM1rInAJfTyXK7N2n6ww1RK4kngsDe6uPgMxKcr', '29-10-2017 06-23-PM'),
(9, '9070251689', '1917', '358168073456736', 1, 'dbnPZCyukuA:APA91bGssmQnScC1lI4xUD0k9GS8YnDN9ub9OdQYDZssge9NvfNDsddqdLQ-4R5utlxluzTzimhAfrWZoA0U6yJbEMcwA3uhBL7mXFtqoK9p97AUhJg-lg7sR5u99eK0ITQuk0jcl_a7', ''),
(10, '8005836811', '9369', '866467034992564', 1, 'diAqf7EURqM:APA91bG9ANvFkCoNNHp3dpE1aBX2LVclNR4LhPL2Ss_5XmG5o3bY0VMdJg8nMPRkt8F2XWcjxu1IJ4y-eUOeQZbyyxnLGPhw0KJKzm1_wVM4XYdusXT8ZbM3NzjUsl9XutqrWwiR7JiD', ''),
(11, '7869234669', '4564', '911440900500642', 1, 'fulAVoGhWfs:APA91bEjCMgVEM3Oef3fzWNcbkhralz21th59gyUzB6tnw5LhIJkdYRhkx59f6Pjvy6aaseV1bUU8bpFsi4xMXCoBQPIDQ1_N7cfUYSnsd441Qf9f86gQSJQGpeiBJpyGF8_VEf1oeGo', ''),
(12, '956866', '4861', '911440900500642', 0, 'ekly4cFsa50:APA91bF3AyaqssuJcn7cBd5GBv63rC96MJIAhOTs5QLbADbObySxgO3jJR7szMnvrSmqukX9SO3Ngntg_BUvwtxrLC6e6EK4RqsC-D3YzAefoJu7riersAsMN47y_ZdAGLsZjqLPFYVM', ''),
(13, '993366368', '5084', '911440900500642', 1, 'ekly4cFsa50:APA91bF3AyaqssuJcn7cBd5GBv63rC96MJIAhOTs5QLbADbObySxgO3jJR7szMnvrSmqukX9SO3Ngntg_BUvwtxrLC6e6EK4RqsC-D3YzAefoJu7riersAsMN47y_ZdAGLsZjqLPFYVM', ''),
(14, '9911258121', '3773', '911440900500642', 1, 'ekly4cFsa50:APA91bF3AyaqssuJcn7cBd5GBv63rC96MJIAhOTs5QLbADbObySxgO3jJR7szMnvrSmqukX9SO3Ngntg_BUvwtxrLC6e6EK4RqsC-D3YzAefoJu7riersAsMN47y_ZdAGLsZjqLPFYVM', ''),
(15, '7999948463', '2161', '866467034992564', 1, 'diAqf7EURqM:APA91bG9ANvFkCoNNHp3dpE1aBX2LVclNR4LhPL2Ss_5XmG5o3bY0VMdJg8nMPRkt8F2XWcjxu1IJ4y-eUOeQZbyyxnLGPhw0KJKzm1_wVM4XYdusXT8ZbM3NzjUsl9XutqrWwiR7JiD', '30-10-2017 01-09-PM'),
(16, '7999984832', '6889', '911440900500642', 1, 'ekly4cFsa50:APA91bF3AyaqssuJcn7cBd5GBv63rC96MJIAhOTs5QLbADbObySxgO3jJR7szMnvrSmqukX9SO3Ngntg_BUvwtxrLC6e6EK4RqsC-D3YzAefoJu7riersAsMN47y_ZdAGLsZjqLPFYVM', ''),
(17, '99766464', '6607', '911440900500642', 1, 'cFAZliGoQPw:APA91bF61i6oOPYBAYwTM5CBJ4wGI02P2lOCI-ZFuVYim_iqPYGeZ3fSOYdlLWCu7Lu3t1h06Y3RpQ4qpWRwb1G1k8StN3nQEsEYBk6XsDZRon-08A1FKsU31fmaXXvIRvskk1rObrWI', ''),
(18, '98', '9549', '911440900500642', 1, 'cFAZliGoQPw:APA91bF61i6oOPYBAYwTM5CBJ4wGI02P2lOCI-ZFuVYim_iqPYGeZ3fSOYdlLWCu7Lu3t1h06Y3RpQ4qpWRwb1G1k8StN3nQEsEYBk6XsDZRon-08A1FKsU31fmaXXvIRvskk1rObrWI', ''),
(19, '986655', '1826', '911440900500642', 0, 'cEZMVIE-T4o:APA91bHg9BGd_JPkIUYyAKQZe6U2Z-orO9xONtzo_3TUFHEaD5kPIcsUcS1IjlcQrgO-vWnv5Mf7ksTqTs8RCgoi58Wa4BTN_sc7Rc0UXVuiChkjIt05NJ28htonnCxTN9ndib2XFc8w', ''),
(20, '99771256', '1170', '911440900500642', 1, 'cEZMVIE-T4o:APA91bHg9BGd_JPkIUYyAKQZe6U2Z-orO9xONtzo_3TUFHEaD5kPIcsUcS1IjlcQrgO-vWnv5Mf7ksTqTs8RCgoi58Wa4BTN_sc7Rc0UXVuiChkjIt05NJ28htonnCxTN9ndib2XFc8w', ''),
(21, '88754646', '9250', '867371028986567', 1, 'fhqGvfz_ROQ:APA91bF3PX3NplGl1c69k-W3_iwshyW6LoDlIFoTW2aN-nARvKb8zgXG9lld_Jk0j9zdFg6UMLVAws_qZFBlkUcRI_f61UJLSO74Df0zpCNnWf4go_2bHVbjwoUsiC4s6tJxP422MMt7', ''),
(22, '96175286', '2439', '867371028986567', 1, 'fhqGvfz_ROQ:APA91bF3PX3NplGl1c69k-W3_iwshyW6LoDlIFoTW2aN-nARvKb8zgXG9lld_Jk0j9zdFg6UMLVAws_qZFBlkUcRI_f61UJLSO74Df0zpCNnWf4go_2bHVbjwoUsiC4s6tJxP422MMt7', ''),
(23, '955655', '5771', '867371028986567', 0, 'fhqGvfz_ROQ:APA91bF3PX3NplGl1c69k-W3_iwshyW6LoDlIFoTW2aN-nARvKb8zgXG9lld_Jk0j9zdFg6UMLVAws_qZFBlkUcRI_f61UJLSO74Df0zpCNnWf4go_2bHVbjwoUsiC4s6tJxP422MMt7', ''),
(24, '9636963', '2407', '867371028986567', 0, 'fhqGvfz_ROQ:APA91bF3PX3NplGl1c69k-W3_iwshyW6LoDlIFoTW2aN-nARvKb8zgXG9lld_Jk0j9zdFg6UMLVAws_qZFBlkUcRI_f61UJLSO74Df0zpCNnWf4go_2bHVbjwoUsiC4s6tJxP422MMt7', ''),
(25, '96176158', '1820', '867371028986567', 1, 'fhqGvfz_ROQ:APA91bF3PX3NplGl1c69k-W3_iwshyW6LoDlIFoTW2aN-nARvKb8zgXG9lld_Jk0j9zdFg6UMLVAws_qZFBlkUcRI_f61UJLSO74Df0zpCNnWf4go_2bHVbjwoUsiC4s6tJxP422MMt7', ''),
(26, '63965586', '8423', '867371028986567', 1, 'fhqGvfz_ROQ:APA91bF3PX3NplGl1c69k-W3_iwshyW6LoDlIFoTW2aN-nARvKb8zgXG9lld_Jk0j9zdFg6UMLVAws_qZFBlkUcRI_f61UJLSO74Df0zpCNnWf4go_2bHVbjwoUsiC4s6tJxP422MMt7', ''),
(27, '85568268', '9728', '867371028986567', 1, 'fhqGvfz_ROQ:APA91bF3PX3NplGl1c69k-W3_iwshyW6LoDlIFoTW2aN-nARvKb8zgXG9lld_Jk0j9zdFg6UMLVAws_qZFBlkUcRI_f61UJLSO74Df0zpCNnWf4go_2bHVbjwoUsiC4s6tJxP422MMt7', ''),
(28, '686886', '1647', '867371028986567', 1, 'fhqGvfz_ROQ:APA91bF3PX3NplGl1c69k-W3_iwshyW6LoDlIFoTW2aN-nARvKb8zgXG9lld_Jk0j9zdFg6UMLVAws_qZFBlkUcRI_f61UJLSO74Df0zpCNnWf4go_2bHVbjwoUsiC4s6tJxP422MMt7', ''),
(29, '8953565', '5472', '867371028986567', 1, 'fhqGvfz_ROQ:APA91bF3PX3NplGl1c69k-W3_iwshyW6LoDlIFoTW2aN-nARvKb8zgXG9lld_Jk0j9zdFg6UMLVAws_qZFBlkUcRI_f61UJLSO74Df0zpCNnWf4go_2bHVbjwoUsiC4s6tJxP422MMt7', ''),
(30, '5532953', '6680', '867371028986567', 1, 'fhqGvfz_ROQ:APA91bF3PX3NplGl1c69k-W3_iwshyW6LoDlIFoTW2aN-nARvKb8zgXG9lld_Jk0j9zdFg6UMLVAws_qZFBlkUcRI_f61UJLSO74Df0zpCNnWf4go_2bHVbjwoUsiC4s6tJxP422MMt7', ''),
(31, '9617615850', '7777', '867371028986567', 0, 'cCF9gnUldis:APA91bFNCrwuayypWu9PynIYh-GhKv18Mf91MT7hLZBc6D2o-ymD31vbEu_A5PQdLHKFvreh1vuAzQT2WFIhKnoF70tCmcQc-d_pCkVG0kJ7TDTiDJjx795bpFDQiaQRa3AS-AOfbh7p', '02-11-2017 01-18-PM'),
(32, '961765860', '4622', '911440900500642', 1, 'c-ldMVPr_FY:APA91bFgdMC-XPXFlem0FYNSOARRIRsypaUHRhf9QlGgg9kvTtL7NSC5GZ6YhSfrA376PdnpqdvZa8aud8TWRJRh7C9kLMjyrMDDLFV3r0oSllpAOp40f524O0dxYBsD8wtc3MXk4bDZ', ''),
(33, '9617615890', '9300', '911440900500642', 1, 'c-roGbGJBIE:APA91bHEIT6qOHChi-Vj0MhAXEqR4d-aht9S2zs309IrikTJsOt6Vn9t98PcwmWinXrd3CysGOWXwR48_aNpYjz6toIIqW48VQBKhVgLZ3ORXJ9vG8vosXyXF_3qh7K_sNpetPhYY7z8', ''),
(34, '+918390783793', '8892', '862857034425017', 1, 'd_5Gb8vmyCY:APA91bHZhUEWxMwrApKkaPjkHTCF4L4pDudgZts1nkiYTMptiSAWnj_0-nG1zGFPHJzy18Fe1t3NDRogJs-Ydf9VOZkzbhWzfoTTCzaHao37Ct-4pdMGgvM-XMtYjof-6rveixiqFfJH', ''),
(35, '992647964', '3526', '867371028986567', 1, 'cm2Yq3EkWrk:APA91bEk4o1SM0kqoELMCza4NrPstJ5xbPsVwcpkqDtDimfTssd6a9O8UCk8-fxexgf-S1b0AlmssyrX-u3wTUh1b3HBIAk5hiD9qK-xe0XC8oQYAWTOtmskuWk_e6fygZrCXGh8jelC', ''),
(36, '4785693210', '2113', '911440900500642', 1, 'ecsATDvRKpY:APA91bHc-kNHlzwWyMbBMILZ5jkwo99HUI4pksWIDgB1BCJxnnL6rj6bZ037xxvYVjLgfzaefnfML7c9oI2hXobJwXecdgtuydZ5bPJ9pkaiOXiuOgF0P7zIdh26ndzHB1ITXh3-9Ngc', '29-10-2017 07-52-PM'),
(37, '9039744391', '3377', '866409034792503', 1, 'cJOUqUpLCb4:APA91bHsnPxosvafg_amrbAWPnV5QhUZR-3yyu7M8O5h0Mljg8Lu1cGpjVTRmGN8Z_3YqhzIb_56TeYchJSMO1z2mKQpwcW_Hel97JzewxZx1C_i0EdwM6DZwr0OnDi39adpAz00xt8N', '03-11-2017 12-57-PM'),
(38, '969696969', '3272', '911440900500642', 1, 'cwjAipCdWrI:APA91bFn-IXEvEEhVX1Jow1FlYj5_pNYKgA5S_tbRG0xp8eLkjVPNddPmKIFwVvwA7S5J1piE5Bb0JnMKXW3qj8jkf7uXrIslbHmFPBazMxvZi6He2SXGT1JbkdQGxs9-5KszXC3hV5S', ''),
(39, '96969696', '7272', '911440900500642', 1, 'eIRuwlpCglM:APA91bHA9o-XS1sEGZvIEeTKpEl6fylh6GDRGj_DgKs-_Fn_Ad78TYA583RPf9iCsYVw_jAEKcDN6yKwYwKPgdz1c36A_8B4yiXyQqY2GPgOOP7jm6BLmPQg48aaacZtROCBpKBIxvQf', '29/10/2017 12.43.PM'),
(40, '903974436', '2326', '911440900500642', 0, 'cyJnXi_ltXg:APA91bE34XfrG0gEhRRIrwLQ8gGALOnoHeRzLSxmYlPvraDkXqWI_a6VClaqZ2i6asKB258q9aH5tpC1-0Nj_85GfqJVf_AQ3ggEjwXY-Oqh-5SmozKCyJBnEdvy5FgtxaMyWDZkTJQo', ''),
(41, '9039744364', '1411', '911440900500642', 1, 'cyJnXi_ltXg:APA91bE34XfrG0gEhRRIrwLQ8gGALOnoHeRzLSxmYlPvraDkXqWI_a6VClaqZ2i6asKB258q9aH5tpC1-0Nj_85GfqJVf_AQ3ggEjwXY-Oqh-5SmozKCyJBnEdvy5FgtxaMyWDZkTJQo', ''),
(42, '791790871', '9654', '353285067861849', 1, 'ex4De9DsL0E:APA91bEa_f8SRJaPuFHxb4aiH69GRfCFGHooCs4v4dNR8jFiCoL4w8Y45kcP6Xw7oDutpQ3bgw7tH4z9gTUgGXStBKKIbDGnPkx2ZoIs8ULahmyezh2wzhcGh1jLvjS2bZdhNHLKiOgN', '29-10-2017 06-33-PM'),
(43, '799948463', '4357', '861598030213053', 1, 'frdUUVGHddU:APA91bH61-dnrYPmHe0Q6svXpOmXtuNtMWEEJmWKUWTw7VJCjnvGi1hsSVKS58SkHIAp-jjfdS2cBZkfl9PmgpRF6Mr4qOgo_DB3bEFRZN5rkT8hYpWMMoez-Wc6_p3KOV0Dt1emwQ1X', '30-10-2017 03-01-PM'),
(44, '7999948432', '3908', '861598030213053', 1, 'fCL9LSZD-oM:APA91bEIJ_qAc25XxlRBHDaY0i2HIQMbvlpkQKPEKNd2dEUNuA2fsWqasT1c8J9fQawzTdFczqZQtEVWPkhkYHQz560XN3d7z1V5ELZ2c1xgDFTMYAoYQdGaFXOdErSL1j8A8jRD1mk7', '15-11-2017 08-04-PM'),
(45, '9039744361', '3758', '862168030240693', 0, 'dUBIcPdFO0M:APA91bFUy_7vKXyTawHR2a_hDHVruY3B7BZnUl0aeip4W41fOs9zubePA3ET0FhYeAMQ8KruKzbHtWpu-u6PBd3FkYWZZj4EahY8IYm4MX9fqybOxdMXmmZxcBrzWSFkUJf1BuqRNSrc', '09-11-2017 01-33-PM'),
(46, '1478523990', '3211', '867371028986567', 0, 'e2wbAtnuKB4:APA91bEfB743eR-bx9UTPoiwSRaruh-3OocjBCf69yqCZEvYyrvdLsuCMM6JZDAYsb57bNHx1wU7PmDpkTtTmhw4oRHH7-poVYftjsi2Y06AftYQ27NRIQx8DKg9rEKQRJMNoGYI-hCZ', ''),
(47, '1234567890', '1584', '867371028986567', 1, 'cM7tZeiwfh0:APA91bEhpBMDz6-Tc9-cmU9bypYxwQWbXLkEeMzQkKjiXkd-4TnLVgxS1U3p_j6oYPBDVmbdcL4EXk8Pww6o-ToXGSuRsmHoM6IkMvPqZRD-ne0iJzfCs7Kzkg_7Xg2Suq2mYykr7xdl', '10-11-2017 07-12-PM'),
(48, '445236980', '4779', '867371028986567', 1, 'e2wbAtnuKB4:APA91bEfB743eR-bx9UTPoiwSRaruh-3OocjBCf69yqCZEvYyrvdLsuCMM6JZDAYsb57bNHx1wU7PmDpkTtTmhw4oRHH7-poVYftjsi2Y06AftYQ27NRIQx8DKg9rEKQRJMNoGYI-hCZ', ''),
(49, '152369870', '3625', '867371028986567', 1, 'e2wbAtnuKB4:APA91bEfB743eR-bx9UTPoiwSRaruh-3OocjBCf69yqCZEvYyrvdLsuCMM6JZDAYsb57bNHx1wU7PmDpkTtTmhw4oRHH7-poVYftjsi2Y06AftYQ27NRIQx8DKg9rEKQRJMNoGYI-hCZ', ''),
(50, '4785692120', '4914', '911440900500642', 1, 'ecsATDvRKpY:APA91bHc-kNHlzwWyMbBMILZ5jkwo99HUI4pksWIDgB1BCJxnnL6rj6bZ037xxvYVjLgfzaefnfML7c9oI2hXobJwXecdgtuydZ5bPJ9pkaiOXiuOgF0P7zIdh26ndzHB1ITXh3-9Ngc', '31-10-2017 03-42-PM'),
(51, '778661236', '3577', '353285067861849', 1, 'ex4De9DsL0E:APA91bEa_f8SRJaPuFHxb4aiH69GRfCFGHooCs4v4dNR8jFiCoL4w8Y45kcP6Xw7oDutpQ3bgw7tH4z9gTUgGXStBKKIbDGnPkx2ZoIs8ULahmyezh2wzhcGh1jLvjS2bZdhNHLKiOgN', '31-10-2017 04-04-PM'),
(52, '9696986856', '9300', '867371028986567', 1, 'c27jIPFji14:APA91bG9fn1ZGfca30jlIYKSK_OQEBqwr0ajEFRHfN2_s0IEQihPcAJAI_nbiiULIjRuK-IiAtR28RmKTLfjf5XsBAsks8CCldlC08KbrNHqYFK0wH8KQKmoPWJBJCKyh54dglr0jcZv', '01-11-2017 06-21-PM'),
(53, '8878765084', '6598', '867371028986567', 1, 'c27jIPFji14:APA91bG9fn1ZGfca30jlIYKSK_OQEBqwr0ajEFRHfN2_s0IEQihPcAJAI_nbiiULIjRuK-IiAtR28RmKTLfjf5XsBAsks8CCldlC08KbrNHqYFK0wH8KQKmoPWJBJCKyh54dglr0jcZv', '01-11-2017 06-56-PM'),
(54, '9713396913', '6217', '867371028986567', 0, 'cCF9gnUldis:APA91bFNCrwuayypWu9PynIYh-GhKv18Mf91MT7hLZBc6D2o-ymD31vbEu_A5PQdLHKFvreh1vuAzQT2WFIhKnoF70tCmcQc-d_pCkVG0kJ7TDTiDJjx795bpFDQiaQRa3AS-AOfbh7p', '10-11-2017 06-49-PM'),
(55, '9183907837973', '9304', '862857034425017', 1, 'ediJvhpNpP4:APA91bE_E3bvWi7ZpNVgSpf4y-WwI64inO-goOp1HnCITbIDRxXm_oZdvmCkEPNOTHsVuwIaL3NJmOUWzFtF_tEdVim42GZmD8KrLI3HFbP3DSR_CwsAtv3wyHFeHp6dBzbV02xGEbWv', ''),
(56, '778556245', '6020', '867306030929163', 1, '123456789', '15-11-2017 05-43-PM'),
(57, '9977180399', '8395', '867371028986567', 1, 'cA6-fIsYuxU:APA91bGEGOvLKd5JXs2cscvA2k-ynd1fEpL5-J0N8gEaeJPULchmWhHtk_k9mi5KJtICuST8rrlaAoNxnrC4_LXnwjZDghPOQAqhaAhyZ6gOT7gyIuPHVyDmBV1M7h3DCAcZZx4_sd5Q', '11-11-2017 01-58-PM'),
(58, '968571230', '2381', '867371028986567', 1, 'fl0LXtGlElM:APA91bGLPaYJ_2eqiJKUwiFnrnSEtfs0yiytGMHSMCPqTeVdS31HXPqZOXQLmFve7BdENRdeQI50Wuf3wJQQcdchCOClj2sXGTrLdEXlm5-28h2-72vxZFORqcddcxeK5_voHPfNSMel', '02-11-2017 06-59-PM'),
(59, '9039741361', '7813', '354994057930212', 1, 'fIK5iCy5p20:APA91bFnx75vI5rS25XyZOJNnNZMZXQnBfKwqwOepTLWRe4sSxiuZzPraEyp1i-F9t4YfSRIJ1v3CxCDoX_iAj-GpzrvWTQecQK2WNeUrk5oE0zDYqiF1Skf13mCxgZJTDk6Wa_x0nH5', '02-11-2017 07-29-pm'),
(60, '5757557455', '1141', '868139029649149', 1, '1234555', '03-11-2017 12-47-AM'),
(61, '7725824787', '3866', '867371028986567', 1, 'fl0LXtGlElM:APA91bGLPaYJ_2eqiJKUwiFnrnSEtfs0yiytGMHSMCPqTeVdS31HXPqZOXQLmFve7BdENRdeQI50Wuf3wJQQcdchCOClj2sXGTrLdEXlm5-28h2-72vxZFORqcddcxeK5_voHPfNSMel', ''),
(62, '9865321470', '4447', '868139029649149', 1, '123456789', '03-11-2017 12-49-PM'),
(63, '8521470963', '3014', '868139029649149', 1, '123456789', '03-11-2017 12-59-PM'),
(64, '1235684709', '9159', '911440900500642', 1, 'erwIjSTSyog:APA91bHkbze_5hoLq46ighK1lVOeKNjWsIIA0tZmWCqjwoPhmOLTLVjKOGTS-o8NguSC9UIdMOoy2xKG9LU7Z9EHK6TuxBMPvtz9NoOJUiD4qBDO6LW_JvjaTD2pOavw-JvMsvCeAnns', '03-11-2017 01-12-PM'),
(65, '+918871860504', '6223', '864216032432756', 1, 'dYoiIKsO3mg:APA91bGv5VHImEAWMIuW9Y90yoCeKfO0zPOGY80PAV-fDRzO5O55iiNNneKjpAGjyZLtRvBf7niosLLxBHo2I-ifLR51_AirOQSCSEOV8jNDG1VukDD7VMWNKKT9Di9h-1S3tTq8AjKP', ''),
(66, '2587413690', '6602', '911440900500642', 1, 'cSLpCqPQoj8:APA91bFodI2a8VmsEgoS9mf_PnC_zai6Ouimf2eUNNqRUfpmaC-yzzq-xfu-zgoQVe5geQ9IJNuC_9BJt8S7H_zniryg5Du0eXUSpR7rf7dcgNd683_q83UbQqo8OYxEuT6E_LozeBS0', '03-11-2017 01-16-PM'),
(67, '2369857410', '6784', '911440900500642', 1, 'cDhJUgePb30:APA91bH3MPOa7w4LYCyN36YY08Xo4_pzCUHFcxyzA7csfPx-q-aZoA8PqadPgPJj5Z6ScGZN3OxgHLe4I92Ml2yfCCoXcddQ9iWouFwlPMEVfr3UfAGt8vevErJZMIxVDHPfCC_aVFU5', '03-11-2017 03-49-PM'),
(68, '+918871860804', '6951', '864216032432756', 1, 'dYoiIKsO3mg:APA91bGv5VHImEAWMIuW9Y90yoCeKfO0zPOGY80PAV-fDRzO5O55iiNNneKjpAGjyZLtRvBf7niosLLxBHo2I-ifLR51_AirOQSCSEOV8jNDG1VukDD7VMWNKKT9Di9h-1S3tTq8AjKP', ''),
(69, '1245789630', '5414', '911440900500642', 1, 'cDhJUgePb30:APA91bH3MPOa7w4LYCyN36YY08Xo4_pzCUHFcxyzA7csfPx-q-aZoA8PqadPgPJj5Z6ScGZN3OxgHLe4I92Ml2yfCCoXcddQ9iWouFwlPMEVfr3UfAGt8vevErJZMIxVDHPfCC_aVFU5', '03-11-2017 04-09-PM'),
(70, '778556245', '6020', '867306030929163', 1, '123456789', '15-11-2017 05-43-PM'),
(71, '756505403', '6377', '353514061942978', 1, 'fjKj4iUQMsI:APA91bEvtYgukdHZPsnxNABWeIL2iCqEhKF2lfiPuNAF6XLsRfLcI3nv04QE-F_FKFt_GI9DyUfELInyPtwR3VlfGX-DQC9WHUuUaAa0ZCcPwf-6sVEA0kk4OryuZjvGzPO6U7x8ox3W', '25-11-2017 09-05-am'),
(72, '782236635', '7361', '352419086614142', 1, 'e_bReTDt-2U:APA91bEvyBZWy6jSAFVUxOquiV5CsH3rFBS2LsQcbNs-QIjNIq3ldufeAdt4Ostzm8H_Q2HB8G9mzr9VUU9DvRDWNCFXEDYTSmgSSLC7M46ZJkGrSKfkuWBPiuRdeAFteISoIqykE4ML', '07-11-2017 11-23-AM'),
(73, '778556254', '3121', '867306030929163', 1, '123456789', '06-11-2017 09-30-PM'),
(74, '9658741230', '7192', '868139029649149', 1, '123456789', '08-11-2017 06-34-PM'),
(75, '1245780963', '3187', '868139029649149', 1, '123456789', '08-11-2017 07-04-PM'),
(76, '9926473964', '4781', '867371028986567', 0, 'ddP0jfak-k0:APA91bEUZK79Y2b45bGc0b8tDT5t8ojkYlOECNUCjhfdELCB5zSafrLpBOXbWi4qEkkIjIu-k0B2dlrCQc3od70MvgQReIpakiS9FLXb6SBRgKxeky7M5E2xzIE5jgv43P5FwDjfM7Kq', '08-11-2017 07-33-PM'),
(77, '786934669', '9420', '911440900500642', 1, 'fulAVoGhWfs:APA91bEjCMgVEM3Oef3fzWNcbkhralz21th59gyUzB6tnw5LhIJkdYRhkx59f6Pjvy6aaseV1bUU8bpFsi4xMXCoBQPIDQ1_N7cfUYSnsd441Qf9f86gQSJQGpeiBJpyGF8_VEf1oeGo', ''),
(78, '1452369870', '6456', '911440900500642', 0, 'fulAVoGhWfs:APA91bEjCMgVEM3Oef3fzWNcbkhralz21th59gyUzB6tnw5LhIJkdYRhkx59f6Pjvy6aaseV1bUU8bpFsi4xMXCoBQPIDQ1_N7cfUYSnsd441Qf9f86gQSJQGpeiBJpyGF8_VEf1oeGo', ''),
(79, '786923466', '8511', '911440900500642', 1, 'fulAVoGhWfs:APA91bEjCMgVEM3Oef3fzWNcbkhralz21th59gyUzB6tnw5LhIJkdYRhkx59f6Pjvy6aaseV1bUU8bpFsi4xMXCoBQPIDQ1_N7cfUYSnsd441Qf9f86gQSJQGpeiBJpyGF8_VEf1oeGo', '09-11-2017 06-48-PM'),
(80, '8770916175', '2804', '862168030240693', 0, 'fmUmc00vex4:APA91bGOUG0ORm3UIyf5Gm7YPmM9T3HCgFFHlS3L54tRBR44LRFBdLbndf3At7Zs-pYfbW5D6Plgz4DbTNWloFSXtnHxq3MWzaPgFhQAqw2pfoFUNmDa2EdSvKYkfqaFg0NJIX1dwOWB', ''),
(81, '1245789063', '1413', '867371028986567', 1, 'dRuCFh6ZHMY:APA91bGCnK_sJrSBKFHIzMpjKBXCGPJCEWJJyoA1FazQXzqX9NoLM6atcU_2MXOvrrqHA71u9zhQbMYIs6pFtqk7IXZ9nbiRwP5MRDbdQt3Kn6E2Qg8Sv5n_zOK7XEehwEDId32M9F2b', '09-11-2017 05-56-PM'),
(82, '59868689', '6894', '866409034792503', 1, 'dsxFrOfeeZQ:APA91bF62v6NegK-0i6H-OGvsAawWo2bCZn0yM5GU6iJZ7e2Ahf3hDG1HRQXQsTpfLrj9u7nEjtuQBdwI5W9rRIv-_uxHNaNi11XPGE9Dd8ONdmtrg5xUYE2W2zQ1HaYpq7jlGckjhnt', '09-11-2017 06-35-PM'),
(83, '7869243622', '4314', '911440900500642', 1, 'fulAVoGhWfs:APA91bEjCMgVEM3Oef3fzWNcbkhralz21th59gyUzB6tnw5LhIJkdYRhkx59f6Pjvy6aaseV1bUU8bpFsi4xMXCoBQPIDQ1_N7cfUYSnsd441Qf9f86gQSJQGpeiBJpyGF8_VEf1oeGo', '09-11-2017 06-49-PM'),
(84, '961615860', '8232', '867371028986567', 1, 'dCr-THop92E:APA91bEo3AjDcUL37zOwGXdVDDwBABzbImLx8NNGXCfbSPdtCMkiOeBqh6z09svQUhunUvngkuU_rUzbgh9NhcGFMILExyDQ5TSVV3b4QBSILXOygy7cicK3kFFI9tT9kr_8Hywi9951', ''),
(85, '6498768464', '4689', '911440900500642', 0, 'fulAVoGhWfs:APA91bEjCMgVEM3Oef3fzWNcbkhralz21th59gyUzB6tnw5LhIJkdYRhkx59f6Pjvy6aaseV1bUU8bpFsi4xMXCoBQPIDQ1_N7cfUYSnsd441Qf9f86gQSJQGpeiBJpyGF8_VEf1oeGo', ''),
(86, '68876888809', '9480', '911440900500642', 0, 'fulAVoGhWfs:APA91bEjCMgVEM3Oef3fzWNcbkhralz21th59gyUzB6tnw5LhIJkdYRhkx59f6Pjvy6aaseV1bUU8bpFsi4xMXCoBQPIDQ1_N7cfUYSnsd441Qf9f86gQSJQGpeiBJpyGF8_VEf1oeGo', ''),
(87, '9617615868', '9076', '867371028986567', 0, 'fCBPdEbwCns:APA91bHUfS-wF9jGub4aB5J3i5rPewYcoMTtQcczdmjXCpW4gdmrIBSL9TNwyHFm0KPNhEkI__Ifyqp0dqhJYKdyP6eHlFSM7PfByzp1E378VQZAKt5D3hZk0ReoJnlK1Cg9eOJaB2A6', ''),
(88, '9898989898', '7493', '862168030240693', 0, 'dhxe5s8Znes:APA91bGgdQSFnMjUgQY7QMTfcLd3COXC1uYfKvB--CfZq-8Heu0OT_YrtJGzy5L2xP2QZ1FVCcmLBiY3NMQ9LB05mslfrmwrSfl-s7HIhR-zeeySY9SQzXkwxkvTCQ5WMlNavQDn7sXe', ''),
(89, '792790187', '1625', '867306030929163', 1, '123456789', '18-11-2017 08-26-AM'),
(90, '782604165', '9640', '358168073456736', 0, 'dxrT1q_JQyo:APA91bHZ64JJccNoT_tAr-y60vHuqZeW-RKedlNfiGZR4tOHWwYetuw9-Il9FfTEHYWeTDKVJdqIhqeQVaw5J703V4XV5K2Nq3JYY4am5Y08dQReBLdIEgKdUz4C9UwX17luR_eZltit', ''),
(91, '0782604165', '1220', '358168073456736', 0, 'dxrT1q_JQyo:APA91bHZ64JJccNoT_tAr-y60vHuqZeW-RKedlNfiGZR4tOHWwYetuw9-Il9FfTEHYWeTDKVJdqIhqeQVaw5J703V4XV5K2Nq3JYY4am5Y08dQReBLdIEgKdUz4C9UwX17luR_eZltit', ''),
(92, '0794953361', '2535', '358168073456736', 1, 'dxrT1q_JQyo:APA91bHZ64JJccNoT_tAr-y60vHuqZeW-RKedlNfiGZR4tOHWwYetuw9-Il9FfTEHYWeTDKVJdqIhqeQVaw5J703V4XV5K2Nq3JYY4am5Y08dQReBLdIEgKdUz4C9UwX17luR_eZltit', ''),
(93, '782034077', '8628', '358073079873449', 1, 'ec84OHFdsN8:APA91bHZxjl9pF8y_5NhTfI9IbOVOnVkJcvWmQQKpdF4bv5Q-JeJ-8ICBsbrELk5S3zydUQD0RYLjXs9D-O5Ha2QKZ3Hp-IlW1aUU00CjYH5gmC7DI0Cp3Jya1JlSOWxxPmjLmES4tTY', '11-11-2017 10-38-AM'),
(94, '', '1245', '867371028986567', 1, 'cQaNZv-Axu8:APA91bHbKL4mRj2cIh49mMYoEOi7lit-W246Etm5N4bcZj9EmKjaPeRQHTAG93bzwJhlrNuJsnz1_fehuaN2YENeDhiQueriDEeKQ6Z8KGtZ1RIJgchUVrI8SrEa_cdLVD0Qh5HaS1Rn', '29-11-2017 05-51-PM'),
(95, '9977180396', '5631', '867371028986567', 1, 'ddP0jfak-k0:APA91bEUZK79Y2b45bGc0b8tDT5t8ojkYlOECNUCjhfdELCB5zSafrLpBOXbWi4qEkkIjIu-k0B2dlrCQc3od70MvgQReIpakiS9FLXb6SBRgKxeky7M5E2xzIE5jgv43P5FwDjfM7Kq', '14-11-2017 05-53-PM'),
(96, '952', '5136', '867371028986567', 0, 'ddP0jfak-k0:APA91bEUZK79Y2b45bGc0b8tDT5t8ojkYlOECNUCjhfdELCB5zSafrLpBOXbWi4qEkkIjIu-k0B2dlrCQc3od70MvgQReIpakiS9FLXb6SBRgKxeky7M5E2xzIE5jgv43P5FwDjfM7Kq', ''),
(97, '9617615860', '1788', '867371028986567', 1, 'dASMFcUKRkE:APA91bGBFyTNyYGlrhjzn2-XhcWtjfvbATzdPb21IPoH6iX-phQG2QEiZT6i9Em2Sz70tolF1IkPrVmwdl-Odx-Nvgk54KmZmO60u4EcvHdXGt_PYs40SL5q0DCjhGatQkkRqFtbsftK', '16-11-2017 05-59-PM'),
(98, '785604165', '7409', '358168073456736', 0, 'dxrT1q_JQyo:APA91bHZ64JJccNoT_tAr-y60vHuqZeW-RKedlNfiGZR4tOHWwYetuw9-Il9FfTEHYWeTDKVJdqIhqeQVaw5J703V4XV5K2Nq3JYY4am5Y08dQReBLdIEgKdUz4C9UwX17luR_eZltit', ''),
(99, '0793372760', '4444', '358168073456736', 1, 'dxrT1q_JQyo:APA91bHZ64JJccNoT_tAr-y60vHuqZeW-RKedlNfiGZR4tOHWwYetuw9-Il9FfTEHYWeTDKVJdqIhqeQVaw5J703V4XV5K2Nq3JYY4am5Y08dQReBLdIEgKdUz4C9UwX17luR_eZltit', '16-11-2017 08-22-pm'),
(100, '9069744361', '3973', '862168030240693', 0, 'eR5FrOQBnaQ:APA91bFc1GdhMr6AMtCjcDXSk0RPVARY9PMdik9fYp1g27aCSPEJmN6JOWRIrSUU_fjiqVbxNftyXizP5EzKP5lKePjcRbUheZLWkT3e1_En8CpwVSZXSfbzyU9qTvDeXrhe0W9zgBu7', ''),
(101, '8390783793', '2395', '862857034425017', 1, 'f0-cmzYkamU:APA91bGJBeIjcoedeKik76rh_Epa3a_QXz17hJCXx7rrtj8k8KYqsqB31OSLtCYZgMHmv0IHF9Uy82TVHhUlxI7uJgOVtra04A0BXQmkP2CI_-aRYP17WEweKwxNh4j44dQtRHSYISlL', '11-12-2017 12-53-PM');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

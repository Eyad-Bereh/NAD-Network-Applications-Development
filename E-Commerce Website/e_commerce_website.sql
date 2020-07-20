-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 18, 2020 at 12:47 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `e_commerce_website`
--
CREATE DATABASE IF NOT EXISTS `e_commerce_website` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `e_commerce_website`;

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Laptops'),
(2, 'Desktops'),
(3, 'Gaming'),
(4, 'Monitors'),
(5, 'Tablets'),
(6, 'Computer Accessories'),
(7, 'Networking'),
(8, 'Computer Components'),
(9, 'Storage & Hard Drives');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(17);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `price` int(11) NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `image` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `price`, `description`, `image`) VALUES
(1, 'ROG Zephyrus M Thin and Portable Gaming Laptop', 1945, '<p>ROG Zephyrus M Thin and Portable Gaming Laptop, 15.6” 240Hz FHD IPS, NVIDIA GeForce RTX 2070, Intel Core i7-9750H, 16GB DDR4 RAM, 1TB PCIe SSD, Per-Key RGB, Windows 10 Home, GU502GW-AH76</p>\r\n<ul>\r\n<li>NVIDIA GeForce RTX 2070 Max-Q 8GB GDDR6 (Base 1080 MHz Boost 1305 MHz TDP 90W)</li>\r\n<li>Latest 9th Gen Intel Core i7-9750H Hexa-core processor</li>\r\n<li>240Hz 3ms 15 6” Full HD (1920x1080) IPS Type pantone validated display</li>\r\n<li>16GB DDR4 2666MHz RAM | 1TB PCIe SSD | Windows 10 Home | Gigabit Wave 2 Wi-Fi 5 (802 11ac 2x2)</li>\r\n<li>Ultra-slim 15’’ metal chassis gaming laptop 18.9 millimeter thin 4.4 pounds</li>\r\n<li>ROG Intelligent Cooling thermal system with dual 12V fans anti-dust technology and adjustable fan modes</li>\r\n<li>ROG Aura sync system with RGB Keyboard</li>\r\n</ul>', 'ROG Zephyrus M Thin and Portable Gaming Laptop.jpg'),
(2, 'MSI GL65 Leopard 10SFK-062', 1679, '<p>MSI GL65 Leopard 10SFK-062 15.6\" FHD 144Hz 3ms Thin Bezel Gaming Laptop Intel Core i7-10750H RTX2070 16GB 512GB NVMe SSD Win 10</p>\r\n<ul>\r\n<li>15.6\" FHD IPS-Level 144Hz 72%NTSC Thin Bezel close to 100%Srgb NVIDIA GeForce RTX 2070 8G GDDR6</li>\r\n<li>Intel Core i7-10750H 2.6-5.0GHz <li>Intel Wi-Fi 6 AX201(2*2 ax)</li>\r\n512GB NVMe SSD 16GB (8G*2)DDR4 2666MHz 2 Sockets Max Memory 64GB</li>\r\n<li>USB 3.1 Gen2 Type C *1 USB 3.2 Gen1 *3 Steel Series per-Key RGB with Anti-Ghost key+ silver lining 720p HD Webcam</li>\r\n<li>Win10 Multi-language Giant Speakers 3W*2 6 cell (51Wh) Li-Ion 230W</li>\r\n</ul>', 'MSI GL65 Leopard 10SFK-062.jpg'),
(3, 'MSI GF65 Thin 9SD-004', 1159, '<p>MSI GF65 Thin 9SD-004 15.6\" 120Hz Gaming Laptop Intel Core i7-9750H GTX1660Ti 16GB 512GB NVMe SSD Win10Home</p>\r\n<ul>\r\n<li>15. 6\" Fhd (1920*1080), IPS-Level 120Hz thin Bezel</li>\r\n<li>Processor: Core i7-9750h 2. 6 - 4. 5GHz</li>\r\n<li>Graphics : NVIDIA GeForce GTX1660Ti 6G GDDR6</li>\r\n<li>Memory: 16GB (8G*2) DDR4 2666MHz 2 Sockets; Max Memory 64GB</li>\r\n<li>Storage: 512GB NVMe SSD</li>\r\n</ul>', 'MSI GF65 Thin 9SD-004.jpg'),
(4, 'Acer Predator Helios 300 Gaming Laptop', 1275, '<p>Acer Predator Helios 300 Gaming Laptop, Intel Core i7-9750H, GeForce GTX 1660 Ti, 15.6\" Full HD 144Hz Display, 3ms Response Time, 16GB DDR4, 512GB PCIe NVMe SSD, RGB Backlit Keyboard, PH315-52-710B</p>\r\n<ul>\r\n<li>9th Generation Intel Core i7-9750H 6-Core Processor (Upto 4. 5 gramHz) with Windows 10 Home 64 Bit</li>\r\n<li>NVIDIA GeForce GTX 1660 Ti Graphics with 6 GB of dedicated GDDR6 VRAM</li>\r\n<li>15. 6\" Full HD (1920 x 1080) Widescreen LED-backlit IPS display (144Hz Refresh Rate, 3ms Overdrive Response Time, 300nit Brightness & 72% NTSC)</li>\r\n<li>16 GB DDR4 2666MHz Memory, 512GB PCIe NVMe SSD (2 x PCIe M. 2 Slots | 1 Slot Open for Easy Upgrades) & 1 - Available Hard Drive Bay</li>\r\n<li>Backlit Keyboard | LAN: Killer Gaming Network E2500 | Wireless: Killer DoubleShot Pro Wireless-AX 1650 WiFi 6 802. 11ac | 4th Gen All-Metal AeroBlade 3D Fan</li>\r\n</ul>', 'Acer Predator Helios 300 Gaming Laptop.jpg'),
(5, 'Razer Blade 15 Gaming Laptop 2020', 1749, '<p>Razer Blade 15 Gaming Laptop 2020: Intel Core i7-10750H 6-Core, NVIDIA GeForce RTX 2060, 15.6\" FHD 1080p 144Hz, 16GB RAM, 512GB SSD, CNC Aluminum, Chroma RGB Lighting, Thunderbolt 3, Black</p>\r\n<ul>\r\n<li>More power:The 10th Gen Intel Core i7-10750H processor provides the ultimate level of performance with up to 5.0 GHz max turbo and 6 cores.</li>\r\n<li>Ray-tracing: The NVIDIA GeForce RTX 2060 is powered by the NVIDIA Turing GPU architecture and brings cinematic-quality rendering to the most visually intense games.</li>\r\n<li>More frames: Incredible performance paired with the fast 144Hz 15. 6\" full HD thin bezel display helps edge out the win.</li>\r\n<li>Thin and compact: The CNC aluminum unibody frame houses incredible performance in the most compact footprint possible, while remaining remarkably durable and just 0.78\" thin.</li>\r\n<li>Ready to connect: Fully loaded with Wi-Fi 6, Gigabit Ethernet, Thunderbolt 3, USB type-A and type-C ports, and HDMI, for a desktop-class experience</li>\r\n<li>Make it yours: Customize the backlight color of the keyboard with Razer Chroma, and expand the memory and storage, making the Razer Blade 15 uniquely yours.</li>\r\n</ul>', 'Razer Blade 15 Gaming Laptop 2020.jpg'),
(6, 'Razer Blade 15 Gaming Laptop', 1305, '<p>Razer Blade 15 Gaming Laptop: Intel Core i7-9750H 6 Core, NVIDIA GeForce GTX 1660 Ti, 15.6\" FHD 144Hz, 16GB RAM, 256GB SSD, CNC Aluminum, Chroma RGB Lighting, Thunderbolt 3, 9th Gen i7-9750H</p>\r\n<ul>\r\n<li>Fully loaded: 15.6\" Full HD, 9th Gen Intel Core i7-9750H, NVIDIA GeForce GTX 1660 Ti , 256GB PCIe SSD, 16GB RAM, thin and compact CNC aluminum unibody (0.78\" x 9.25\" x 13.98\"), anodized Black finish.</li>\r\n<li>Edge-to-edge action: Colorful and accurate visuals to the thin bezel factory calibrated 15.6\" full HD display, for an immersive experience.</li>\r\n<li>Packed with power: Driven by the latest 9th Gen Intel processor and NVIDIA GeForce GTX 1660 Ti, and paired with accessible 16GB dual-channel memory and fast 256GB SSD storage, for amazing performance.</li>\r\n<li>Precision crafted: CNC aluminum unibody houses incredible performance in the most compact footprint possible, while remaining remarkably durable and just 0.78\" thin.</li>\r\n<li>Ready to connect: Plug in multiple USB 3.1 devices directly, expand the view on up to 3 external displays via Thunderbolt 3, MiniDisplayPort and HDMI, and Gigabit Ethernet, for a desktop-class experience.</li>\r\n</ul>', 'Razer Blade 15 Gaming Laptop.jpg\r\n'),
(7, 'HP Pavilion Intel Pentium Gold 4417U', 318, '<p>HP Pavilion Intel Pentium Gold 4417U 4GB 500GB 15.6-inch DVD Windows 10 Laptop (Renewed)</p>\r\n<ul>\r\n<li><Certified Refurbished product has been tested and certified by the manufacturer or by a third-party refurbisher to look and work like new, with limited to no signs of wear. The refurbishing process includes functionality testing, inspection, reconditioning and repackaging. The product ships with relevant accessories, a 90-day warranty, and may arrive in a generic white or brown box. Accessories may be generic and not directly from the manufacturer.</li>\r\n<li>Intel Pentium Gold 4417U (2.3 GHz, 2 MB cache, 2 cores), 4GB DDR4, 500GB HDD, DVDRW</li>\r\n<li>15.6\" diagonal HD SVA BrightView WLED-backlit (1366 x 768)</li>\r\n<li>External ports: 1 HDMI 1.4b; 1 headphone/microphone combo; 1 RJ-45; 1 USB 2.0; 2 USB 3.1 Gen 1 (Data transfer only)</li>\r\n<li>Graphics: Intel HD Graphics 610 and Front-facing HP Webcam with integrated digital microphone</li>\r\n</ul>', 'HP Pavilion Intel Pentium Gold 4417U.jpg'),
(8, 'New Apple MacBook Pro (16-Inch, 16GB RAM, 1TB Storage, 2.3GHz Intel Core i9) - Silver', 2529, '<p>New Apple MacBook Pro (16-Inch, 16GB RAM, 1TB Storage, 2.3GHz Intel Core i9) - Silver</p>\r\n<ul>\r\n<li>Ninth-generation 8-Core Intel Core i9 Processor</li>\r\n<li>Stunning 16-inch Retina Display with True Tone technology</li>\r\n<li>Touch Bar and Touch ID</li>\r\n<li>Amd Radeon Pro 5500M Graphics with GDDR6 memory</li>\r\n<li>Ultrafast SSD</li>\r\n<li>Intel UHD Graphics 630</li>\r\n<li>Six-speaker system with force-cancelling woofers</li>\r\n</ul>', 'New Apple MacBook Pro (16-Inch, 16GB RAM, 1TB Storage, 2.3GHz Intel Core i9) - Silver.jpg'),
(9, 'CybertronPC Patriot Gaming Desktop - AMD A4-6300', 2500, '<p>CybertronPC Patriot Gaming Desktop - AMD A4-6300 Dual-Core 3.7GHz Processor, 8GB DDR3 Memory, Radeon HD 8370D Graphics, 1TB HDD, 802.11bgn Wireless, Microsoft Windows 8.1 64-Bit (Blue) (Discontinued by Manufacturer)</p>\r\n<ul>\r\n<li>System: AMD A4-6300 3.70GHz Dual-Core Processor | AMD 760G Chipset | 8GB DDR3 | 1TB HDD | Genuine Windows 8.1 64-Bit Pre-Installed</li>\r\n<li>Graphics: Radeon HD 8370D GPU | 24X DVD±RW Dual-Layer Drive | Audio: 8 Channel | Gigabit LAN | Keyboard and Mouse</li>\r\n<li>Expansion Bays/Slots Total(Free): 4(3) Ext. 5.25\" | 6(5) Int. 3.5/2.5\" | 1(0) PCI | 1(1) PCI-E x1 | 1(1) PCI-E x16 | 2(1) DIMM 240P</li>\r\n<li>Connectivity: 2x USB 3.0 | 4x USB 2.0 | 1x RJ-45 Network Ethernet 10/100/1000 | Wireless 802.11bgn | Audio | 1x HDMI | 1x VGA</li>\r\n<li>Chassis: Aerocool Strike-X One Gaming Mid-Tower w/400 Watt Power Supply | 1 Year Parts & Labor Warranty | Free Lifetime Tech Support</li>\r\n</ul>', 'CybertronPC Patriot Gaming Desktop - AMD A4-6300.jpg'),
(10, 'Dell OptiPlex 3070 Desktop Computer', 540, '<p>Dell OptiPlex 3070 Desktop Computer - Intel Core i5-9500 - 8GB RAM - 256GB SSD - Small Form Factor</p>\r\n\r\n<ul>\r\n<li>Expand your productivity. Optiplex has over 25 years of experience delivering customer-led innovation for desktops by continuously creating faster and smarter experiences.</li>\r\n<li>Featuring versatile, space-saving form factors with customized deployment options and 9th Gen Intel Core processors.</li>\r\n<li>Work Smart efficiency without limits: ideal for education environments and call centers, these desktops come with the same reliability and space-saving benefits you\'ve come to expect. Easy to maintain and mount, these essential desktops remove all barriers to success.</li>\r\n<li>Adjusted to your needs: The OptiPlex small form Factor all-in-one stand provides integrated cabling for one power cord solution and the aesthetics of an all-in-one with the ultimate Display flexibility your work demands.</li>\r\n<li>Built smart: with at least 39% post-consumer recycled plastics you can rest assured you\'re working smart and sustainably.</li>\r\n<li>Uncompromising productivity power your workday: 9th Gen Intel Core processors drive your workday like never before.</li>\r\n</ul>', 'Dell OptiPlex 3070 Desktop Computer.jpg'),
(11, 'Skytech Archangel Gaming Computer PC Desktop', 1199, '<p></p>\r\n\r\n<ul>\r\n<li>AMD Ryzen 5 3600 6-Core 12-Thread 3.6GHz (4.2 GHz Max Boost) CPU | 500GB SSD – Up to 30x faster than traditional HDD | B450 Motherboard</li>\r\n<li>GeForce RTX 2070 8GB GDDR6 Graphics Card (Brand May Varies) | 16GB DDR4 3000MHz Gaming Memory with Heat Spreaders | Windows 10 Home 64-bit | AMD High Performance Wraith Cooler</li>\r\n<li>802.11AC Wi-Fi | No Bloatware | 3 x DisplayPort 1.4, 1 x HDMI | HD Audio and Mic | Free Gaming Keyboard and Mouse | 2 x USB 3.0, 2 x USB 2.0, 4 x USB 3.2 Gen1</li>\r\n<li>3 x RGB RING Fans for Maximum Air Flow | Powered by 80 Plus Certified 500 Watt Power Supply | Skytech Archangel Gaming Case with Tempered Glass - White</li>\r\n<li>1 Year Warranty on Parts and Labor | Lifetime Free Technical Support | Assembled in the USA | This powerful gaming PC is capable of running all your favorite games such as World of Warcraft, League of Legends, Grand Theft Auto V, Apex Legends, Fortnite, Roblox, PLAYERUNKNOWN’s Battlegrounds, Overwatch, Counter-Strike: Global Offensive, Battlefield V, Minecraft, The Division 2, and more at High to Ultra settings, crisp 1080p Full HD resolution and smooth 60+ FPS game play.</li>\r\n</ul>\r\n', 'Skytech Archangel Gaming Computer PC Desktop.jpg'),
(12, 'Samsung Business CH890 Series', 612, '<p>Samsung Business CH890 Series 34 inch WQHD 3440x1440 Ultrawide Curved Desktop Monitor for Business, 100 Hz, USB-C, HDMI, DP, 3 Year Warranty (LC34H890WJNXGO), Black, Model:LC34H890WJNXZA</p>\r\n<ul>\r\n<li>Greater multi-tasking with a 34”, 21:9 wide screen, Picture-by-Picture and Easy setting box</li>\r\n<li>USB Type-C port to transmit power and data, and DP signals to devices without cable connection</li>\r\n<li>100 Hz Refresh Rate, Wall Mountable AMD Freesync. Power Supply Type - AC 100~240V</li>\r\n<li>Ultra WQHD enables a screen resolution of 3440x1440, 3000:1 </li>\r\n<li>Contrast Ratio</li>\r\n100 hertz\r\n</ul>', 'Samsung Business CH890 Series.jpg'),
(13, 'Samsung 32 inch CF391 Curved Monitor (LC32F391FWNXZA)', 279, '<p>Samsung 32 inch CF391 Curved Monitor (LC32F391FWNXZA) - 1080p, Dual Monitor, Laptop Monitor, Monitor Stand/Riser/Mount Compliant, AMD Freesync, Gaming, HDMI, White</p>\r\n<ul>\r\n<li>1800R curvature of the screen provides a truly immersive viewing experience</li>\r\n<li>An ultra-slim and sleek profile that measures less than 05 inch thick The simple circular stand will add a modern look to your space. Product Dimensions With Stand- 28.5 x 20.6 x 9.8 Inches. Product Dimensions Without Stand- 28.5 x 16.9 x 3.7 Inches</li>\r\n<li>Game Mode technology which allows you to enjoy smooth images, even during the fastest moving scenes. Power Consumption (Stand-by) Less than 0.3Watt</li>\r\n<li>Experience vibrant, stunningly vivid colors with Samsung\'s Active Crystal Color technology The excellent 5000:1 contrast ratio delivers deep blacks and bright whites</li>\r\n<li>Energy Saving Plus reduces screen brightness to save power, plus the screen brightness automatically transitions fluidly—reducing energy use even moreWindows Compatible:Yes</li>\r\n</ul>', 'Samsung 32 inch CF391 Curved Monitor (LC32F391FWNXZA).jpg'),
(14, 'Acer Predator XB271HU', 649, '<p>Acer Predator XB271HU bmiprz 27\" WQHD (2560x1440) NVIDIA G-SYNC IPS Monitor, (Display Port & HDMI Port, 144Hz), Black</p>\r\n<ul>\r\n<li>27 Inches WQHD (2560 x 1440) widescreen with NVIDIA G SYNC technology</li>\r\n<li>Refresh rate: 144Hz (overclocking to 165Hz) Using display port</li>\r\n<li>Response time: 4ms, Pixel Pitch: 0. 233mm. Flicker less technology reduces annoying screen flickering that can cause eye strain when viewing the monitor for long periods.</li>\r\n<li>2 x 2W speakers. Brightness: 350 nit. Panel technology: In plane switching (IPS) Technology. Tilt angle 5° to 35°. swivel angle 60°. Maximum adjustable Height 5. 91 inches</li>\r\n<li>Signal inputs: 1 x HDMI (V1. 4) & 1 x Display port. Viewing angle: 178° (H)/ 178° (V)</li>\r\n<li>Mounting type: VESA compatibility Mountable 100 x 100 millimeter</li>\r\n<li>NOTE: Kindly refer to the user manual provided as a PDF manual in the product description section</li>\r\n</ul>', 'Acer Predator XB271HU.jpg'),
(15, 'Apple iPad Pro (12.9-inch, Wi-Fi + Cellular, 256GB) - Space Gray (4th Generation)', 1199, '<p>Apple iPad Pro (12.9-inch, Wi-Fi + Cellular, 256GB) - Space Gray (4th Generation)</p>\r\n<ul>\r\n<li>12.9-inch edge-to-edge Liquid Retina display with ProMotion, True Tone, and P3 wide color</li>\r\n<li>A12Z Bionic chip with Neural Engine</li>\r\n<li>12MP Wide camera, 10MP Ultra Wide camera, and LiDAR Scanner</li>\r\n<li>7MP TrueDepth front camera</li>\r\n<li>Face ID for secure authentication and Apple Pay</li>\r\n<li>Four speaker audio and five studio-quality microphones</li>\r\n<li>802.11ax Wi-Fi 6 and Gigabit-class LTE cellular data</li>\r\n</ul>', 'Apple iPad Pro (12.9-inch, Wi-Fi + Cellular, 256GB) - Space Gray (4th Generation).jpg'),
(16, 'All-new Fire HD 8 tablet, 8\" HD display, 32 GB, designed for portable entertainment, Black', 89, '<p>All-new Fire HD 8 tablet, 8\" HD display, 32 GB, designed for portable entertainment, Black</p>\r\n<ul>\r\n<li>8\" HD display, 2X the storage (32 or 64 GB of internal storage and up to 1 TB with microSD card) + 2 GB RAM.</li>\r\n<li>All-day battery life - Up to 12 hours of reading, browsing the web, watching videos, and listening to music.</li>\r\n<li>Now with USB-C for easier charging. Fully charges in under 5 hours (with included cable + adapter).</li>\r\n<li>30% faster thanks to the new 2.0 GHz quad-core processor.</li>\r\n<li>Enjoy your favorite apps like Netflix, Facebook, Hulu, Instagram, TikTok, and more through Amazon’s Appstore (Google Play not supported).</li>\r\n<li>Introducing Game Mode - A distraction-free and optimized gaming experience.</li>\r\n<li>Hands-free with Alexa, including on/off toggle.</li>\r\n</ul>', 'All-new Fire HD 8 tablet, 8\" HD display, 32 GB, designed for portable entertainment, Black.jpg'),
(17, 'VicTsing Wireless Keyboard and Mouse Combo - Energy Saving, 3 Level DPI Adjustable Wireless Mouse and 2.4GHz Computer Keyboard, Independent On/Off Switch, Num/Caps/Power Indicator, Black', 26, '<p>VicTsing Wireless Keyboard and Mouse Combo [Energy Saving], 3 Level DPI Adjustable Wireless Mouse and 2.4GHz Computer Keyboard, Independent On/Off Switch, Num/Caps/Power Indicator, Black</p>\r\n<ul>\r\n<li>FULL SIZED WIRELESS KEYBOARD: **Note: the 12 Multimedia shortcuts are not compatible with Mac system.** Standard US layout (QWERTY) with number pad, each key is independently and appropriately spaced to prevent hitting the wrong key simultaneously. 12 multimedia hotkeys combined with FN help you to play music and movie, browse the web, open email and more, extremely convenient and efficient.</li>\r\n<li>COMFORTABLE TYPING EXPERIENCE: There are built-in foldable stands at back of this computer keyboard, 8 degree tilt angle for the great typing position. Ergonomic designs, pace-welled chiclet keycaps offers smoother touch. The keys are not very loud, but also not so silent, the perfect balance for your pleasant typing.</li>\r\n<li>MUTE WIRELESS MOUSE: This Cordless mouse provides whisper-quiet clicking, never worry about disturbing others to rest while working or playing. DPI adjustable mouse delivers 3-level of DPI (800/1200/1600) to cater for your different needs in sensitivity.</li>\r\n<li>HASSLE FREE CONNECTION: Plug and play, one tiny receiver connects with both your keyboard and mouse combo by only sharing one USB port, no other driver or option needed. 2.4G wireless technology is adopted to provide more stable connectivity and up to 33ft effective wireless range.</li>\r\n<li>LONGER BATTERY LIFE: The ergonomic wireless keyboard is powered by 1 AAA battery, and the wireless mouse is powered by 2 AAA batteries (batteries are not included). The mouse will enter into sleep mode after 10min disuse but you can wake it up by just one clicking. Both mouse and keyboard have an on/off switch, turn them off when not use.</li>\r\n</ul>', 'VicTsing Wireless Keyboard and Mouse Combo - Energy Saving.jpg'),
(18, 'BENGOO G9000 Stereo Gaming Headset', 38, '<p>BENGOO G9000 Stereo Gaming Headset for PS4, PC, Xbox One Controller, Noise Cancelling Over Ear Headphones with Mic, LED Light, Bass Surround, Soft Memory Earmuffs for Laptop Mac Nintendo PS3 Games</p>\r\n<ul>\r\n<li>【MULTI-PLATFORM COMPATIBLE】Support PlayStation 4, New Xbox One, PC, Nintendo 3DS, Laptop, PSP, Tablet, iPad, Computer, Mobile Phone. Please note you need an extra Microsoft Adapter (Not Included) when connect with an old version Xbox One controller.</li>\r\n<li>【SURROUNDING STEREO SUBWOOFER】Clear sound operating strong brass, splendid ambient noise isolation and high precision 40mm magnetic neodymium driver, acoustic positioning precision enhance the sensitivity of the speaker unit, bringing you vivid sound field, sound clarity, shock feeling sound. Perfect for various games like Halo 5 Guardians, Metal Gear Solid, Call of Duty, Star Wars Battlefront, Overwatch, World of Warcraft Legion, etc.</li>\r\n<li>【NOISE ISOLATING MICROPHONE】Headset integrated onmi-directional microphone can transmits high quality communication with its premium noise-concellng feature, can pick up sounds with great sensitivity and remove the noise, which enables you clearly deliver or receive messages while you are in a game. Long flexible mic design very convenient to adjust angle of the microphone.</li>\r\n<li>【GREAT HUMANIZED DESIGN】Superior comfortable and good air permeability protein over-ear pads, muti-points headbeam, acord with human body engineering specification can reduce hearing impairment and heat sweat.Skin friendly leather material for a longer period of wearing. Glaring LED lights desigend on the earcups to highlight game atmosphere.</li>\r\n<li>【EFFORTLESSLY VOLUME CONTROL】High tensile strength, anti-winding braided USB cable with rotary volume controller and key microphone mute effectively prevents the 49-inches long cable from twining and allows you to control the volume easily and mute the mic as effortless volume control one key mute.</li>\r\n</ul>', 'BENGOO G9000 Stereo Gaming Headset.jpg'),
(19, 'Elgato Stream Deck - Live Content Creation Controller', 199, '<p>Elgato Stream Deck - Live Content Creation Controller with 15 Customizable LCD Keys, Adjustable Stand, for Windows 10 and macOS 10.13 or late</p>\r\n<ul>\r\n<li>15 LCD keys: Tap to switch scenes, launch media, adjust audio and more</li>\r\n<li>Fully customizable: Personalize keys with custom icons or choose from hundreds</li>\r\n<li>Direct integration: Control game capture, OBS, XSplit, TipeeeStream, Twitch, YouTube, Mixer, and more</li>\r\n<li>Easy setup: simply drag and drop actions onto keys in the app</li>\r\n<li>Unlimited control: Nest folders within folders to store as many actions as you want</li>\r\n</ul>', 'Elgato Stream Deck - Live Content Creation Controller.jpg'),
(20, 'HP 56 | Ink Cartridge | Black | C6656AN', 39, '<p>HP 56 | Ink Cartridge | Black | C6656AN</p>\r\n<ul>\r\n<li>HP 56 ink cartridges work with: HP Deskjet 450, 5550, 5650, 5850, 9650, 9680. HP Officejet 4215, 5610, 6110.</li>\r\n<li>HP Photosmart 7260, 7350, 7450, 7550, 7755, 7760, 7762, 7960. HP PSC 1210, 1315, 1350, 2110, 2175, 2210, 2410.</li>\r\n<li>Up to 2x more prints with Original HP ink vs refill cartridges.</li>\r\n<li>Cartridge yield (approx.): 520 pages</li>\r\n<li>Original HP ink cartridges: genuine ink for your HP printer.</li>\r\n<li>What\'s in the box: 1 New Original HP 56 ink cartridge (C6656AN)</li>\r\n<li>Color: Black</li>\r\n</ul>', 'HP 56 | Ink Cartridge | Black | C6656AN.jpg'),
(21, 'GAOMON PD1161 11.6 Inches Tilt Support Drawing Pen Display', 239, '<p>GAOMON PD1161 11.6 Inches Tilt Support Drawing Pen Display with 8192 Levels Pressure Sensitive Battery Free Pen AP50 and 8 Shortcut Keys</p>\r\n<ul>\r\n<li>[11.6 INCHES 1080P HD IPS MONITOR]--GAOMON PD1161 is a professional drawing display. 11.6 Inch screen offering you sufficient space with 256.32×144.18mm (10.09*5.68 inch) working area. Screen Resolution: 1920x1080 (16:9); Report Rate: 266RPS; Accuracy:±0.5mm (Center) \\±3mm(Corner); Reading Height:10mm; Signal Output: Mini HDMI&Type-C; Viewing angle: 178°; Resolution: 5080LPI.</li>\r\n<li>[±60 DEGREES OF TILT FUNCTION]--±60 Degree Pen Tilt Technology allow you to draw freely in various ways based on subtle differences in pressure, which ensured detailed modification to be realized.[8 SHORTCUT KEYS]-- 8 programmable express keys are placed on left side surface of PD1161 Digital Pen Display. You can set the express keys based on your preference, like zoom in/out, scroll up and down, and more.</li>\r\n<li>[BATTERY-FREE PEN & 8192 LEVELS PEN PRESSURE ]-- The pen adopts the battery free electromagnetic resonance technology. There is no need to charge the pen or replace battery, which is an environment-friendly design.With high 8192 levels pen pressure, it can clearly display different lines with different thickness and depth according to the strength of using pen.</li>\r\n<li>[OS SUPPORT& PROGRAM COMPATIBILITY]-- Support Windows 7/8/8.1, Mac OS 10.12 or later; Compatible with most drawing program: Adobe Photoshop, Illustrator, Clip Studio, Lightroom, Sketchbook Pro, Manga Studio, CorelPainter, FireAlpaca, OpenCanvas, Paint Tool SAI2, Krita and so on; [WIDELY APPLICATION]-- Drawing, photography, animation, fashion, sketching, image editing, 3D sculpture, drawing beginners, painting enthusiasts, architectural designers ect.</li>\r\n<li>[WARM REMINDER]-- GAOMON PD1161 drawing pen display must be used with a computer.GAOMON provides lifetime technical support for all Our Drawing Pen Tablets/Displays.Any question,welcome to contact us any time,GAOMON service department always be there for you.</li>\r\n</ul>', 'GAOMON PD1161 11.6 Inches Tilt Support Drawing Pen Display.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `products_categories`
--

DROP TABLE IF EXISTS `products_categories`;
CREATE TABLE `products_categories` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products_categories`
--

INSERT INTO `products_categories` (`id`, `product_id`, `category_id`) VALUES
(1, 1, 1),
(2, 1, 3),
(3, 2, 1),
(4, 2, 3),
(5, 3, 1),
(6, 3, 3),
(7, 4, 1),
(8, 4, 3),
(9, 5, 1),
(10, 5, 3),
(11, 6, 1),
(12, 6, 3),
(13, 7, 1),
(14, 8, 1),
(15, 9, 2),
(16, 9, 3),
(17, 10, 2),
(18, 11, 2),
(19, 11, 3),
(20, 12, 4),
(21, 13, 4),
(22, 14, 3),
(23, 14, 4),
(24, 15, 5),
(25, 16, 3),
(26, 16, 5),
(27, 17, 6),
(28, 18, 3),
(29, 18, 6),
(30, 19, 6),
(31, 20, 6),
(32, 21, 6);

-- --------------------------------------------------------

--
-- Table structure for table `purchases`
--

DROP TABLE IF EXISTS `purchases`;
CREATE TABLE `purchases` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `timestamp` int(11) NOT NULL,
  `session_id` varchar(36) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchases`
--

INSERT INTO `purchases` (`id`, `user_id`, `product_id`, `quantity`, `price`, `timestamp`, `session_id`) VALUES
(12, 1, 1, 2, 3890, 1595020258, 'a8ddcc89-f5b8-4308-92c8-c8d6444d5793'),
(13, 1, 20, 10, 390, 1595020258, 'a8ddcc89-f5b8-4308-92c8-c8d6444d5793'),
(14, 1, 11, 1, 1199, 1595020258, 'a8ddcc89-f5b8-4308-92c8-c8d6444d5793'),
(15, 1, 14, 1, 649, 1595020258, 'a8ddcc89-f5b8-4308-92c8-c8d6444d5793'),
(16, 1, 1, 1, 1945, 1595025469, 'c1ec184b-44ea-4bec-8dc3-7c33a1fd828f');

-- --------------------------------------------------------

--
-- Table structure for table `SPRING_SESSION`
--

DROP TABLE IF EXISTS `SPRING_SESSION`;
CREATE TABLE `SPRING_SESSION` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `SPRING_SESSION_ATTRIBUTES`
--

DROP TABLE IF EXISTS `SPRING_SESSION_ATTRIBUTES`;
CREATE TABLE `SPRING_SESSION_ATTRIBUTES` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(256) COLLATE utf8_bin NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `password` varchar(1024) COLLATE utf8_bin NOT NULL,
  `balance` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `username`, `password`, `balance`) VALUES
(1, 'john.doe@domain.com', 'John Doe', '123456789', 75718),
(2, 'someone@example.com', 'Someone', 'ABCDEFG123456', 100);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products_categories`
--
ALTER TABLE `products_categories`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `purchases`
--
ALTER TABLE `purchases`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `SPRING_SESSION`
--
ALTER TABLE `SPRING_SESSION`
  ADD PRIMARY KEY (`PRIMARY_ID`),
  ADD UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  ADD KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  ADD KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`);

--
-- Indexes for table `SPRING_SESSION_ATTRIBUTES`
--
ALTER TABLE `SPRING_SESSION_ATTRIBUTES`
  ADD PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `products_categories`
--
ALTER TABLE `products_categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `purchases`
--
ALTER TABLE `purchases`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `products_categories`
--
ALTER TABLE `products_categories`
  ADD CONSTRAINT `products_categories_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`Id`),
  ADD CONSTRAINT `products_categories_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `purchases`
--
ALTER TABLE `purchases`
  ADD CONSTRAINT `purchases_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `SPRING_SESSION_ATTRIBUTES`
--
ALTER TABLE `SPRING_SESSION_ATTRIBUTES`
  ADD CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `SPRING_SESSION` (`PRIMARY_ID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

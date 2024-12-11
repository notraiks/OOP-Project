DROP DATABASE IF EXISTS lost_and_found;
CREATE DATABASE lost_and_found;
USE lost_and_found;


CREATE TABLE `found_items` (
  `item_id` int(11) NOT NULL,
  `item_name` varchar(255) NOT NULL,
  `category` varchar(100) NOT NULL,
  `location_found` varchar(255) NOT NULL,
  `date_found` date NOT NULL,
  `time_found` time NOT NULL,
  `description` text NOT NULL,
  `status` enum('unclaimed','claimed') NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



INSERT INTO `found_items` (`item_id`, `item_name`, `category`, `location_found`, `date_found`, `time_found`, `description`, `status`, `user_id`) VALUES
(22, 'Black wallet', 'Accessories', 'Library', '2024-11-01', '10:30:00', 'Brown leather wallet with cash inside.', 'claimed', 1),
(23, 'Aqua flask Skyblue', 'Accessories', 'Cafeteria', '2024-11-02', '12:45:00', 'Skyblue flask, stainless steel, medium size.', 'claimed', 1),
(24, 'Porsche key', 'Accessories', 'Parking Lot', '2024-11-03', '15:20:00', 'Set of keys with Porsche tag.', 'unclaimed', 1),
(25, 'Blue Backpack', 'Bags & Backpacks', 'Library', '2024-11-04', '18:00:00', 'Blue backpack with a laptop sleeve.', 'claimed', 1),
(26, 'Red Shoes', 'Footwear', 'Gym', '2024-11-05', '07:45:00', 'Pair of red running shoes, size 9.', 'unclaimed', 1),
(27, 'Green Sunglasses', 'Eyewear', 'Cafeteria', '2024-11-06', '19:30:00', 'Green-tinted sunglasses with black frame.', 'claimed', 1),
(28, 'Leather Jacket', 'Clothing', 'Auditorium', '2024-11-07', '09:10:00', 'Black leather jacket, size medium.', 'unclaimed', 1),
(29, 'Smartphone', 'Mobile Devices', 'Bus Stop', '2024-11-08', '14:25:00', 'iPhone 13 with a cracked screen.', 'claimed', 1),
(30, 'Keys', 'Accessories', 'Parking Lot', '2024-11-09', '11:15:00', 'Set of car and house keys.', 'unclaimed', 1),
(31, 'Camera', 'Electronics', 'Classroom A', '2024-11-10', '16:50:00', 'Canon DSLR camera with lens and bag.', 'claimed', 1),
(32, 'Black Wallet', 'Wallets & Purses', 'Classroom B', '2024-11-11', '11:20:00', 'Black leather wallet with cash and cards.', 'unclaimed', 2),
(33, 'Silver Watch', 'Accessories', 'Library', '2024-11-12', '14:35:00', 'Silver wristwatch with a black band.', 'unclaimed', 2),
(34, 'Laptop Charger', 'Electronics', 'Cafeteria', '2024-11-13', '12:05:00', 'Laptop charger for MacBook, 60W.', 'claimed', 2),
(35, 'Sports T-Shirt', 'Clothing', 'Gym', '2024-11-14', '10:25:00', 'White sports T-shirt, size L.', 'unclaimed', 2),
(36, 'Red Wallet', 'Wallets & Purses', 'Parking Lot', '2024-11-15', '08:15:00', 'Red wallet with cards and ID inside.', 'claimed', 2),
(37, 'Sunglasses', 'Eyewear', 'Classroom A', '2024-11-16', '17:00:00', 'Brown sunglasses with yellow lenses.', 'unclaimed', 2),
(38, 'Water Bottle', 'Tumblers', 'Cafeteria', '2024-11-17', '13:00:00', 'Blue water bottle with a sports cap.', 'claimed', 2),
(39, 'Power Bank', 'Electronics', 'Gym', '2024-11-18', '09:30:00', '5000mAh power bank, brand new.', 'unclaimed', 2),
(40, 'Handbag', 'Bags & Backpacks', 'Library', '2024-11-19', '14:45:00', 'Black leather handbag with gold trim.', 'claimed', 2),
(41, 'Android Phone', 'Mobile Devices', 'Parking Lot', '2024-11-20', '11:00:00', 'Android phone with screen crack.', 'unclaimed', 2),
(42, 'Gloves', 'Clothing', 'Classroom B', '2024-11-21', '12:10:00', 'Black leather gloves, size M.', 'claimed', 2),
(43, 'Keychain', 'Accessories', 'Cafeteria', '2024-11-22', '08:40:00', 'Silver keychain with a small souvenir attached.', 'unclaimed', 3),
(44, 'Baseball Cap', 'Clothing', 'Bus Stop', '2024-11-23', '09:25:00', 'Red baseball cap with a logo on the front.', 'claimed', 3),
(45, 'Bluetooth Speaker', 'Electronics', 'Library', '2024-11-24', '13:30:00', 'Portable Bluetooth speaker, waterproof.', 'unclaimed', 3),
(46, 'Earrings', 'Accessories', 'Auditorium', '2024-11-25', '16:10:00', 'Silver hoop earrings.', 'claimed', 3),
(47, 'Pillow', 'Accessories', 'Gym', '2024-11-26', '10:00:00', 'Fluffy pillow with a floral pattern.', 'unclaimed', 3),
(48, 'Charger', 'Electronics', 'Classroom A', '2024-11-27', '17:55:00', 'Phone charger for Samsung Galaxy.', 'claimed', 3),
(49, 'Tote Bag', 'Bags & Backpacks', 'Library', '2024-11-28', '12:30:00', 'Large canvas tote bag, light brown color.', 'unclaimed', 3),
(50, 'Notebook', 'Stationery', 'Cafeteria', '2024-11-29', '09:20:00', 'Green notebook with lined pages.', 'claimed', 3),
(51, 'Waterproof Watch', 'Accessories', 'Classroom B', '2024-11-30', '15:00:00', 'Waterproof wristwatch with a rubber strap.', 'unclaimed', 4),
(52, 'Bicycle Lock', 'Accessories', 'Bus Stop', '2024-12-01', '11:00:00', 'Combination bicycle lock, 3-digit.', 'claimed', 4),
(53, 'Ring', 'Accessories', 'Parking Lot', '2024-12-02', '14:15:00', 'Gold ring with a small diamond.', 'unclaimed', 4),
(54, 'Tablet', 'Electronics', 'Library', '2024-12-03', '13:00:00', 'Apple iPad 10th Gen with case.', 'claimed', 4),
(55, 'Gym Towel', 'Accessories', 'Gym', '2024-12-04', '07:45:00', 'Red gym towel, cotton material.', 'unclaimed', 4),
(56, 'Fitness Band', 'Electronics', 'Cafeteria', '2024-12-05', '09:30:00', 'Fitness tracker, new in box.', 'claimed', 4),
(57, 'Phone Case', 'Accessories', 'Bus Stop', '2024-12-06', '12:00:00', 'Clear phone case for iPhone 12.', 'unclaimed', 4),
(58, 'Camera Lens', 'Electronics', 'Library', '2024-12-07', '14:10:00', 'Wide-angle camera lens for DSLR.', 'claimed', 4),
(59, 'Water Bottle', 'Tumblers', 'Gym', '2024-12-08', '17:30:00', 'Stainless steel insulated water bottle.', 'unclaimed', 4),
(60, 'Leather Wallet', 'Wallets & Purses', 'Classroom A', '2024-12-09', '09:00:00', 'Brown leather wallet, compact size.', 'claimed', 4),
(61, 'Travel Bag', 'Bags & Backpacks', 'Cafeteria', '2024-12-10', '10:30:00', 'Large travel bag, black with wheels.', 'unclaimed', 4),
(62, 'Ski Gloves', 'Clothing', 'Parking Lot', '2024-12-11', '13:20:00', 'Black ski gloves, size M.', 'claimed', 4),
(63, 'Fanny Pack', 'Accessories', 'Auditorium', '2024-12-12', '15:40:00', 'Green fanny pack with zippers.', 'unclaimed', 4),
(64, 'Nike Air Force 1 Sneakers', 'Footwear', 'Gym', '2024-12-13', '08:00:00', 'White Nike sneakers, size 10.', 'unclaimed', 1),
(65, 'Ray-Ban Aviator Sunglasses', 'Eyewear', 'Cafeteria', '2024-12-14', '12:15:00', 'Gold frame aviator sunglasses.', 'claimed', 2),
(66, 'Apple AirPods Pro', 'Electronics', 'Library', '2024-12-15', '14:45:00', 'Wireless earbuds with charging case.', 'unclaimed', 3),
(67, 'Blue Jansport Backpack', 'Bags & Backpacks', 'Bus Stop', '2024-12-16', '10:20:00', 'Blue backpack with books and stationery.', 'claimed', 4),
(68, 'Gold Necklace with M Pendant', 'Accessories', 'Auditorium', '2024-12-17', '16:40:00', 'Delicate gold chain with an M pendant.', 'unclaimed', 1),
(69, 'Casio Digital Watch', 'Accessories', 'Parking Lot', '2024-12-18', '09:35:00', 'Black Casio digital wristwatch.', 'claimed', 2),
(70, 'Black Dell Laptop', 'Electronics', 'Classroom A', '2024-12-19', '11:25:00', 'Dell laptop with a cracked corner.', 'unclaimed', 3),
(71, 'Red Leather Wallet', 'Wallets & Purses', 'Library', '2024-12-20', '13:50:00', 'Red wallet with cash and ID.', 'claimed', 4),
(72, 'Stainless Steel Water Bottle', 'Tumblers', 'Gym', '2024-12-21', '08:45:00', 'Insulated water bottle, 1 liter.', 'unclaimed', 1),
(73, 'Samsung Galaxy S22', 'Mobile Devices', 'Cafeteria', '2024-12-22', '15:30:00', 'Black Samsung phone with a clear case.', 'claimed', 2),
(74, 'Tiffany Silver Bracelet', 'Accessories', 'Auditorium', '2024-12-23', '17:55:00', 'Silver bracelet with heart charm.', 'unclaimed', 3),
(75, 'Brown Leather Satchel', 'Bags & Backpacks', 'Classroom B', '2024-12-24', '14:10:00', 'Brown satchel with laptop compartment.', 'claimed', 4),
(76, 'Canon PowerShot Camera', 'Electronics', 'Library', '2024-12-25', '11:15:00', 'Compact digital camera with strap.', 'unclaimed', 1),
(77, 'Grey Nike Hoodie', 'Clothing', 'Gym', '2024-12-26', '09:20:00', 'Grey hoodie, size L.', 'claimed', 2),
(78, 'Fossil Leather Watch', 'Accessories', 'Bus Stop', '2024-12-27', '12:40:00', 'Brown leather strap wristwatch.', 'unclaimed', 3),
(79, 'Beats Headphones', 'Electronics', 'Cafeteria', '2024-12-28', '14:50:00', 'Over-ear wireless headphones, red.', 'claimed', 4),
(80, 'Gold Ring with Diamond', 'Accessories', 'Auditorium', '2024-12-29', '16:30:00', '18k gold ring with a small diamond.', 'unclaimed', 1),
(81, 'Red Puma Backpack', 'Bags & Backpacks', 'Library', '2024-12-30', '10:10:00', 'Red backpack with school supplies.', 'claimed', 2),
(82, 'Silver Hoop Earrings', 'Accessories', 'Classroom A', '2024-12-31', '11:45:00', 'Pair of silver hoop earrings.', 'unclaimed', 3),
(83, 'White Adidas Sneakers', 'Footwear', 'Gym', '2025-01-01', '08:35:00', 'White Adidas sneakers, size 9.', 'claimed', 4),
(84, 'Pink Leather Wallet', 'Wallets & Purses', 'Cafeteria', '2025-01-02', '12:55:00', 'Pink wallet with zipper.', 'unclaimed', 1),
(85, 'Sony Bluetooth Speaker', 'Electronics', 'Bus Stop', '2025-01-03', '15:20:00', 'Portable Bluetooth speaker, blue.', 'claimed', 2),
(86, 'Black Nike Cap', 'Clothing', 'Parking Lot', '2025-01-04', '09:25:00', 'Black cap with white Nike logo.', 'unclaimed', 3),
(87, 'Apple Watch Series 7', 'Accessories', 'Library', '2025-01-05', '13:35:00', 'Smartwatch with a black band.', 'claimed', 4),
(88, 'Silver Keychain with Bell', 'Accessories', 'Classroom B', '2025-01-06', '14:45:00', 'Silver keychain with a small bell.', 'unclaimed', 1),
(89, 'Green Canvas Tote Bag', 'Bags & Backpacks', 'Auditorium', '2025-01-07', '10:15:00', 'Green tote bag with books.', 'claimed', 2),
(90, 'Red Nike Basketball', 'Sports Equipment', 'Gym', '2025-01-08', '08:50:00', 'Red basketball with Nike logo.', 'unclaimed', 3),
(91, 'Leather-Bound Notebook', 'Stationery', 'Cafeteria', '2025-01-09', '11:55:00', 'Brown leather-bound notebook.', 'claimed', 4),
(92, 'White Gold Necklace', 'Accessories', 'Bus Stop', '2025-01-10', '15:45:00', 'White gold chain necklace.', 'unclaimed', 1),
(93, 'Logitech Mouse', 'Electronics', 'Library', '2025-01-11', '12:10:00', 'Wireless mouse, black.', 'claimed', 2),
(94, 'Black Umbrella', 'Accessories', 'Parking Lot', '2025-01-12', '09:05:00', 'Foldable black umbrella.', 'unclaimed', 3),
(95, 'Yellow Scarf', 'Clothing', 'Cafeteria', '2025-01-13', '10:30:00', 'Knitted yellow scarf.', 'claimed', 4),
(96, 'Purple Pencil Case', 'Stationery', 'Classroom A', '2025-01-14', '14:20:00', 'Purple case with pens and pencils.', 'unclaimed', 1),
(97, 'Dell Laptop Charger', 'Electronics', 'Library', '2025-01-15', '11:40:00', 'Laptop charger, 65W.', 'claimed', 2),
(98, 'Blue Baseball Cap', 'Clothing', 'Gym', '2025-01-16', '08:40:00', 'Blue cap with white logo.', 'unclaimed', 3),
(99, 'Black Leather Gloves', 'Clothing', 'Bus Stop', '2025-01-17', '15:10:00', 'Black leather gloves, size M.', 'claimed', 4),
(100, 'Black Leather Belt', 'Accessories', 'Auditorium', '2025-01-18', '10:50:00', 'Black belt with silver buckle.', 'unclaimed', 1);


CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `fName` varchar(50) NOT NULL,
  `lName` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `role` enum('admin','client') NOT NULL DEFAULT 'client'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `users` (`user_id`, `fName`, `lName`, `email`, `phone_number`, `password`, `created_at`, `updated_at`, `role`) VALUES
(1, 'Raiken', 'Ladrera', 'admin1@gmail.com', '1234567891', '1234', '2024-11-28 21:00:14', '2024-12-05 03:45:58', 'admin'),
(2, 'Nadia', 'Bolcia', 'admin2@gmail.com', '09837462841', 'admin', '2024-11-29 23:50:26', '2024-11-30 08:33:09', 'admin'),
(3, 'John', 'Doe', 'user1@gmail.com', '1234567890', '123', '2024-11-23 23:21:46', '2024-11-30 08:33:27', 'client'),
(4, 'Jane', 'Smith', 'user2@gmail.com', '0987654321', 'user', '2024-11-24 00:00:00', '2024-11-30 08:33:33', 'client'),
(11, 'asd', 'asd', 'asd@gmail.com', '1234567891', '1', '2024-12-05 03:44:55', '2024-12-05 03:44:55', 'client');


CREATE TABLE `__efmigrationshistory` (
  `MigrationId` varchar(150) NOT NULL,
  `ProductVersion` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `found_items`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `fk_user_items` (`user_id`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

ALTER TABLE `__efmigrationshistory`
  ADD PRIMARY KEY (`MigrationId`);

ALTER TABLE `found_items`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;


ALTER TABLE `found_items`
  ADD CONSTRAINT `fk_user_items` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE;
COMMIT;


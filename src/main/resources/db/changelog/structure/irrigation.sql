SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Table `plots`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plots` ;

CREATE TABLE plots (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  crop_type VARCHAR(255),
  name VARCHAR(255),
  area DECIMAL(10, 2)
  -- Add other columns as needed
);

-- -----------------------------------------------------
-- Table `time_slots`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `time_slots` ;

-- Create time_slot table
CREATE TABLE time_slots (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  irrigation_water_amount INT NOT NULL,
  irrigation_days INT NOT NULL,
  irrigation_duration INT NOT NULL,
  irrigation_rate INT NOT NULL,
  irrigations_per_day INT NOT NULL,
  plot_id BIGINT NOT NULL,
  FOREIGN KEY (plot_id) REFERENCES plots(id)
);

DROP TABLE IF EXISTS `irrigation_process` ;

-- liquibase/changelog/V1__Create_irrigation_process_table.sql

-- Change Set: V1__Create_irrigation_process_table
-- Description: Creates the irrigation_process table

-- Create irrigation_process table
CREATE TABLE irrigation_process (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  start_time DATE NOT NULL,
  end_time DATE NOT NULL,
  status VARCHAR(255) NOT NULL,
  sensor_retries INT NOT NULL,
  duration INT NOT NULL,
  time_slot_id BIGINT NOT NULL,
  FOREIGN KEY (time_slot_id) REFERENCES time_slots(id)
);

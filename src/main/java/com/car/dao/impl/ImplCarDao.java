package com.car.dao.impl;

import java.sql.Types;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.car.dao.CarDao;
import com.car.dao.entity.CarEntity;
import com.car.dao.entity.CarPaginationEntity;

@Repository("ImplCarDao")
public class ImplCarDao implements CarDao {

	@Autowired
	@Qualifier("carDataSource")
	private DataSource carDataSource;

	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void jdbcTemplate_init() {
		jdbcTemplate = new JdbcTemplate(carDataSource);
	}

	@Override
	public String addUpdateCarInfo(CarEntity carEntity) {

		LobHandler lobHandler = new DefaultLobHandler();
		SqlLobValue sqlLobValue = new SqlLobValue(carEntity.getImage(),
				lobHandler);
		String query = "";
		Object[] carData = {};
		int[] dataType = {};
		if (carEntity.getSno() == 0) {
			query = "insert into cars_tbl(name, price, transmission, warranty, horsepower, image, doe) values(?,?,?,?,?,?,?)";
			carData = new Object[] { carEntity.getName(), carEntity.getPrice(),
					carEntity.getTransmission(), carEntity.getWarranty(),
					carEntity.getHorsePower(), sqlLobValue,
					new java.sql.Date(System.currentTimeMillis()) };
			dataType = new int[] { Types.VARCHAR, Types.INTEGER, Types.VARCHAR,
					Types.INTEGER, Types.VARCHAR, Types.BLOB, Types.DATE };

			/*
			 * System.out.println("entity inserted into dao"+ carEntity); try{
			 * Connection connection = carDataSource.getConnection(); String
			 * query =
			 * "insert into cars_tbl(name, price, transmission, horsepower,doe) values(?,?,?,?,?)"
			 * ; PreparedStatement statement=
			 * connection.prepareStatement(query); statement.setString(1,
			 * carEntity.getName()); statement.setInt(2, carEntity.getPrice());
			 * statement.setString(3, carEntity.getTransmission());
			 * statement.setString(4, carEntity.getHorsePower());
			 * statement.setDate(5, new
			 * java.sql.Date(System.currentTimeMillis()));
			 * statement.executeUpdate();
			 *
			 * }catch(Exception ex){ ex.printStackTrace(); }
			 */
		} else {
			query = "update cars_tbl set name=?, price=?, transmission=?, warranty=?, horsepower=?, image=? where sno=?";
			carData = new Object[] { carEntity.getName(), carEntity.getPrice(),
					carEntity.getTransmission(), carEntity.getWarranty(),
					carEntity.getHorsePower(), carEntity.getImage(),
					carEntity.getSno() };
			dataType = new int[] { Types.VARCHAR, Types.INTEGER, Types.VARCHAR,
					Types.INTEGER, Types.VARCHAR, Types.BLOB, Types.INTEGER };
		}
		jdbcTemplate.update(query, carData, dataType);
		return "Success!!  ..record updated into dao";
	}

	@Override
	public List<CarEntity> findCars() {
		String query = "select sno,name, price, transmission, warranty, horsepower, doe from cars_tbl";
		List<CarEntity> carEntityList = jdbcTemplate.query(query,
				new BeanPropertyRowMapper(CarEntity.class));
		return carEntityList;
	}

	@Override
	public String deleteCarByCid(String cid) {
		String query = "delete from cars_tbl where sno=" + cid;
		int d = jdbcTemplate.update(query);
		return d == 0 ? "fail" : "deleted";
	}

	@Override
	public CarEntity findCarByCid(String cid) {
		String query = "select *from cars_tbl where sno=" + cid;
		CarEntity carEntityList = (CarEntity) jdbcTemplate.queryForObject(
				query, new BeanPropertyRowMapper(CarEntity.class));
		return carEntityList;
	}

	@Override
	public byte[] findImageByCid(String cid) {
		String query = "select * from cars_tbl where sno=" + cid;
		CarEntity carEntity = (CarEntity) jdbcTemplate.queryForObject(query,
				new BeanPropertyRowMapper(CarEntity.class));
		return carEntity.getImage();
	}

	@Override
	public List<String> findAllTransmission() {
		String query = "select ttype from transmission_tbl";
		List<String> carList = jdbcTemplate.queryForList(query, String.class);
		return carList;
	}

	@Override
	public List<CarEntity> findAllCarsByTransmission(String transmissionType) {
		String query = "select sno,name, price, transmission, warranty, horsepower, doe from cars_tbl where transmission=?";
		List<CarEntity> carList = jdbcTemplate.query(query,
				new Object[] { transmissionType }, new BeanPropertyRowMapper(
						CarEntity.class));
		return carList;
	}

	@Override
	public CarPaginationEntity findCarsWithPagination(int startIndex,
			int noOfRecordsPerPage) {

		String query = "select * from cars_tbl limit " + startIndex + ", "+ noOfRecordsPerPage;

		List<CarEntity> carEntityList = jdbcTemplate.query(query,
				new BeanPropertyRowMapper(CarEntity.class));

		CarPaginationEntity carPaginationEntity = new CarPaginationEntity();

		int totalNoOfRecords = jdbcTemplate
				.queryForInt("select count(*) from cars_tbl");

		carPaginationEntity.setNoOfRecords(totalNoOfRecords);
		carPaginationEntity.setCarEntityList(carEntityList);

		return carPaginationEntity;
	}

}

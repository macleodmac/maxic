package com.maxic.towers.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.maxic.towers.web.dao.Tower;
import com.maxic.towers.web.dao.TowerDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/maxic/towers/web/config/dao-context.xml",
		"classpath:com/maxic/towers/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TowerDaoTests {

	@Autowired
	private TowerDao towerDao;

	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("SET SQL_SAFE_UPDATES = 0");
		jdbc.execute("DELETE FROM towers");
		jdbc.execute("SET SQL_SAFE_UPDATES = 1");
	}

//	private Tower tower1 = new Tower(0, "TestDoveId", 1234, "Sheffield",
//			"Crookesmoor", "Road", true, "SC1234", 51.42f, 52.34f,
//			"S6 3FQ", 0.0f, 0.0f, 0, 0, 0, "St James", "II", false, false,
//			false, "", 1234, "St James", "", "");
//	
//	private Tower tower2 = new Tower(0, "newId", 1234, "Bishop's Stortford",
//			"Abbotts", "Way", true, "BS999", 51.42f, 52.34f,
//			"S6 3FQ", 0.0f, 0.0f, 0, 0, 0, "St James", "II", false, false,
//			false, "", 1234, "St Andrew", "", "");
	
	@Test
	public void testAddGetTower() {
		towerDao.addTower(tower1);
		
		List<Tower> towers1 = towerDao.getTowers();
		
		assertEquals("Should be two retrieved users.", 1, towers1.size());
		
		towerDao.addTower(tower2);
		
		List<Tower> towers2 = towerDao.getTowers();
		
		assertEquals("Should be two retrieved users.", 2, towers2.size());
	}
	
	@Test
	public void testGetTowerById() {
		towerDao.addTower(tower1);
		
		List<Tower> towers1 = towerDao.getTowers();
		int id  = towers1.get(0).getTowerId();
		
		Tower tower = towerDao.getTower(id);
		
		assertEquals("Inserted tower should match retrieved", tower, towers1.get(0));
		
	}
	
	@Test
	public void testDeleteTower() {
		towerDao.addTower(tower1);
		
		List<Tower> towers1 = towerDao.getTowers();
		int id  = towers1.get(0).getTowerId();
		
		assertTrue("Tower should be deleted successfully", towerDao.deleteTower(id));
		
	}
	
	@Test
	public void testEditTower() {
		towerDao.addTower(tower1);
		
		List<Tower> towers1 = towerDao.getTowers();
		
		
		
		Tower tower = towers1.get(0);
		System.out.println(tower);
		String dedication = "This is a dedication!";
		tower.setExtraInfo(dedication);
		
		towerDao.editTower(tower);
		towers1 = towerDao.getTowers();
		Tower towerRetrieved = towers1.get(0);
		System.out.println(towerRetrieved);
		
		assertEquals("Tower should be edited successfully", tower, towerRetrieved);
		
	}
	
	
	
	
	
	// TODO - Reimplement this
	@Test
	public void testCreateTower() {
	
		towerDao.addTower(tower1);
		List<Tower> towers = towerDao.getTowers();

		assertEquals("Number of users should be 1.", 1, towers.size());

		int towerId = towers.get(0).getTowerId();
		tower1.setTowerId(towerId);

		Tower towerReturn = towerDao.getTower(towerId);
		System.out.println(towerReturn);
		assertNotNull("There should be one Tower in the database", towerReturn);

		Tower editingTower = new Tower(towerId, "EditedId", 1234,
				"BISHOP'S STORTFORD", "Crookesmoor", "Road", true, "SC1234",
				51.42f, 52.34f, "S6 3FQ", 0.0f, 0.0f, 0, 0, 0, "St James",
				"II", false, false, false, "", 1234, "St James", "", "");


		towerReturn = towerDao.getTower(towerId);
		System.out.println(towerReturn);

		assertTrue("Tower should be deleted successfully.",
				towerDao.deleteTower(towerId));

	}
}

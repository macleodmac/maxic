package com.maxic.towers.web.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
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

	@Test
	public void testCreateTower() {
		Tower tower = new Tower(0, "TestDoveId", 1234, "Sheffield",
				"Crookesmoor", "Road", true, "SC1234", 51.42f, 52.34f,
				"S6 3FQ", 0.0f, 0.0f, 0, 0, 0, "St James", "II", false, false,
				false, "", 1234, "St James", "", "");
		assertTrue("Tower creation should return true",
				towerDao.addTower(tower));

		List<Tower> towers = towerDao.getTowers();

		assertEquals("Number of users should be 1.", 1, towers.size());

		int towerId = towers.get(0).getTowerId();
		tower.setTowerId(towerId);

		Tower towerReturn = towerDao.getTower(towerId);
		System.out.println(towerReturn);
		assertNotNull("There should be one Tower in the database", towerReturn);

		Tower editingTower = new Tower(towerId, "EditedId", 1234,
				"BISHOP'S STORTFORD", "Crookesmoor", "Road", true, "SC1234",
				51.42f, 52.34f, "S6 3FQ", 0.0f, 0.0f, 0, 0, 0, "St James",
				"II", false, false, false, "", 1234, "St James", "", "");

		assertTrue("Tower should be deleted successfully.",
				towerDao.editTower(editingTower));

		towerReturn = towerDao.getTower(towerId);
		System.out.println(towerReturn);

		assertTrue("Tower should be deleted successfully.",
				towerDao.deleteTower(towerId));

	}
}

package xquery;

import org.exist.source.FileSource;
import org.exist.source.Source;
import org.exist.storage.DBBroker;
import org.exist.util.XMLFilenameFilter;
import org.exist.xmldb.DatabaseInstanceManager;
import org.exist.xmldb.XQueryService;
import org.junit.*;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import java.io.File;

import static org.junit.Assert.fail;

public abstract class TestRunner {

    private static File[] files;
	private static Collection testCollection;

    protected abstract String getDirectory();

	@Test
	public void run() {
		try {
			XQueryService xqs = (XQueryService) testCollection.getService("XQueryService", "1.0");
			Source query = new FileSource(new File("test/src/xquery/runTests.xql"), "UTF-8", false);
			for (File file : files) {
				xqs.declareVariable("doc", file.getName());
				ResourceSet result = xqs.execute(query);
				XMLResource resource = (XMLResource) result.getResource(0);
                System.out.println(resource.getContent());
				Element root = (Element) resource.getContentAsDOM();
				NodeList tests = root.getElementsByTagName("test");
				for (int i = 0; i < tests.getLength(); i++) {
					Element test = (Element) tests.item(i);
					String passed = test.getAttribute("pass");
					if (passed.equals("false")) {
						System.err.println(resource.getContent());
						fail("Test " + test.getAttribute("n") + " failed");
					}
				}
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Before
	public void setUpBefore() throws Exception {
		// initialize driver
		Class<?> cl = Class.forName("org.exist.xmldb.DatabaseImpl");
		Database database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");
		DatabaseManager.registerDatabase(database);

		Collection root =
			DatabaseManager.getCollection("xmldb:exist://" + DBBroker.ROOT_COLLECTION, "admin",	null);
		CollectionManagementService service =
			(CollectionManagementService) root.getService("CollectionManagementService", "1.0");
		testCollection = service.createCollection("test");
		Assert.assertNotNull(testCollection);

		File dir = new File(getDirectory());
		files = dir.listFiles(new XMLFilenameFilter());
		for (File file : files) {
			XMLResource resource = (XMLResource) testCollection.createResource(file.getName(), "XMLResource");
			resource.setContent(file);
			testCollection.storeResource(resource);
		}
	}

	@After
	public void tearDownAfter() {
		files = null;
		try {
			DatabaseInstanceManager dim =
			    (DatabaseInstanceManager) testCollection.getService(
			        "DatabaseInstanceManager", "1.0");
			dim.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
        testCollection = null;
	}
}
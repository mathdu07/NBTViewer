package fr.mathdu07.nbtviewer.nms.nbt;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.mathdu07.nbtviewer.nms.NMSManager;

public class NBTTagDoubleTest {

	private static net.minecraft.server.v1_6_R3.NBTTagDouble nmsTag;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		NMSManager.init(NBTTestUtil.CB_VERSION_TESTING);
		nmsTag = new net.minecraft.server.v1_6_R3.NBTTagDouble("", 147.63);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		NMSManager.init("");
	}
	
	@Test
	public void testNMSClass() {
		assertEquals(NBTTagDouble.NMS_CLASS, net.minecraft.server.v1_6_R3.NBTTagDouble.class);
	}
	
	@Test
	public void testCreateTag() {
		assertEquals(new NBTTagDouble(new net.minecraft.server.v1_6_R3.NBTTagDouble("", 4.5)), NBTTagDouble.createTag(4.5));
	}

	@Test
	public void testNBTTagDouble() {
		final NBTTagDouble nbt = new NBTTagDouble(nmsTag);
		assertEquals(nmsTag, nbt.getNmsTag());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNBTTagDoubleException() {
		new NBTTagDouble("a string");
	}
	
	@Test
	public void testGetData() {
		final NBTTagDouble nbt = new NBTTagDouble(nmsTag);
		assertTrue(nbt.getData() == nmsTag.data);
	}
	
	@Test
	public void testGetTypeId() {
		final NBTTagDouble nbt = new NBTTagDouble(nmsTag);
		assertTrue(nmsTag.getTypeId() == nbt.getTypeId());
	}
	
	@Test
	public void testEquals() {
		final NBTTagDouble nbt = new NBTTagDouble(nmsTag), equal = new NBTTagDouble(nmsTag);
		final NBTTagDouble diff = new NBTTagDouble(new net.minecraft.server.v1_6_R3.NBTTagDouble("", 63.1));
		
		assertTrue(nbt.equals(nbt));
		assertTrue(nbt.equals(equal));
		assertFalse(nbt.equals(diff));
	}
	
	@Test
	public void testClone() {
		final NBTTagDouble nbt = new NBTTagDouble(nmsTag), clone = (NBTTagDouble) nbt.clone();
		assertEquals(nbt, clone);
		assertEquals(nbt.getNmsTag(), clone.getNmsTag());
	}
	
	@Test
	public void testHashCode() {
		final NBTTagDouble nbt = new NBTTagDouble(nmsTag);
		assertTrue(nmsTag.hashCode() == nbt.hashCode());
	}

}

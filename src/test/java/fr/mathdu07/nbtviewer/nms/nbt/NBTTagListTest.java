package fr.mathdu07.nbtviewer.nms.nbt;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.mathdu07.nbtviewer.nms.NMSManager;

public class NBTTagListTest {

	private net.minecraft.server.v1_6_R3.NBTTagList nmsTag;
	private net.minecraft.server.v1_6_R3.NBTBase[] nmsList;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		NMSManager.init(NBTTestUtil.CB_VERSION_TESTING);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		NMSManager.init("");
	}
	
	@Before
	public void setUp() throws Exception {
		nmsTag = new net.minecraft.server.v1_6_R3.NBTTagList();
		
		nmsList = new net.minecraft.server.v1_6_R3.NBTBase[2];
		nmsList[0] = new net.minecraft.server.v1_6_R3.NBTTagInt("", 9);
		nmsList[1] = new net.minecraft.server.v1_6_R3.NBTTagString("", "a string");
		
		for (int i = 0; i < nmsList.length; i++)
			nmsTag.add(nmsList[i]);
	}
	
	@Test
	public void testNMSClass() {
		assertEquals(NBTTagList.NMS_CLASS, net.minecraft.server.v1_6_R3.NBTTagList.class);
	}

	@Test
	public void testNBTTagList() {
		final NBTTagList nbt = new NBTTagList(nmsTag);
		assertEquals(nmsTag, nbt.getNmsTag());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNBTTagListException() {
		new NBTTagList("a string");
	}
	
	@Test
	public void testAdd() {
		final net.minecraft.server.v1_6_R3.NBTTagList nmsTagClone = (net.minecraft.server.v1_6_R3.NBTTagList) nmsTag.clone();
		final NBTTagList tag = new NBTTagList(nmsTag);
		tag.add(new NBTTagInt(nmsList[0]));
		nmsTagClone.add(nmsList[0]);
		
		assertEquals(tag.nmsTag, nmsTagClone);
	}
	
	@Test
	public void testSize() {
		final NBTTagList tag = new NBTTagList(nmsTag);
		assertTrue(tag.size() == nmsList.length);
		
		tag.add(new NBTTagInt(new net.minecraft.server.v1_6_R3.NBTTagInt("", 3)));
		assertTrue(tag.size() == nmsList.length + 1);
	}
	
	@Test
	public void testGet() {
		final NBTTagList tag = new NBTTagList(nmsTag);
		
		for (int i = 0; i < nmsList.length; i++)
			assertEquals(tag.get(i).nmsTag, nmsList[i]);
	}
	
	@Test
	public void testGetTypeId() {
		final NBTTagList nbt = new NBTTagList(nmsTag);
		assertTrue(nmsTag.getTypeId() == nbt.getTypeId());
	}
	
	@Test
	public void testEquals() {
		final NBTTagList nbt = new NBTTagList(nmsTag), equal = new NBTTagList(nmsTag);
		final NBTTagList diff = new NBTTagList(new net.minecraft.server.v1_6_R3.NBTTagList());
		
		assertTrue(nbt.equals(nbt));
		assertTrue(nbt.equals(equal));
		assertFalse(nbt.equals(diff));
	}
	
	@Test
	public void testClone() {
		final NBTTagList nbt = new NBTTagList(nmsTag), clone = (NBTTagList) nbt.clone();
		assertEquals(nbt, clone);
		assertEquals(nbt.getNmsTag(), clone.getNmsTag());
	}
	
	@Test
	public void testHashCode() {
		final NBTTagList nbt = new NBTTagList(nmsTag);
		assertTrue(nmsTag.hashCode() == nbt.hashCode());
	}

}

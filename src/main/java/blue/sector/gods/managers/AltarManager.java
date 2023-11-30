package blue.sector.gods.managers;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;

public class AltarManager {
    private static AltarManager instance;

    public static AltarManager instance() {
	if (instance == null)
	    instance = new AltarManager();
	return instance;
    }

    private AltarManager() {}

    public boolean isAltarSign(Block block) {
	if ((block == null) || (block.getType() != Material.OAK_WALL_SIGN))
	    {
		return false;
	    }
	BlockData bdata = block.getBlockData();
	if (!(bdata instanceof Directional)) {
	    return false;
	}
	Directional directional = (Directional) bdata;

	Block altarBlock = block.getRelative(directional.getFacing().getOppositeFace());
	//if (getGodTypeForAltarBlockType(altarBlock.getType()) == null) {
	//		return false;
	//}
	if ((!altarBlock.getRelative(BlockFace.UP).getType().equals(Material.SOUL_TORCH)) &&(!altarBlock.getRelative(BlockFace.UP).getType().equals(Material.TORCH)) && (!altarBlock.getRelative(BlockFace.UP).getType().equals(Material.REDSTONE_TORCH))) {
			return false;
	}
	return true;


    }
}

package hn.blacknight0981.wit.api.utils;

import org.bukkit.Material;

public enum Materials {
    AIR(Material.AIR, Material.VOID_AIR, Material.CAVE_AIR);

    private final Material[] materials;

    Materials(Material... material) {
        this.materials = material;
    }

    public static boolean contains(Material material) {
        for (Materials matEnum : Materials.values()) {
            for (Material m : matEnum.materials) {
                if (m == material) {
                    return true;
                }
            }
        }
        return false;
    }
}

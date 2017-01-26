/**
 * 
 */
package com.rayzr522.creativelynamedlib.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.rayzr522.creativelynamedlib.utils.text.TextUtils;

/**
 * @author Rayzr
 *
 */
public class ItemFactory {

    private ItemStack base;

    private ItemFactory(ItemStack base) {
        this.base = base.clone();
    }

    /**
     * Creates a new ItemFactory with the given item as a base
     * 
     * @param base The base item to use
     * @return The new {@link ItemFactory}
     */
    public static ItemFactory of(ItemStack base) {
        Objects.requireNonNull(base, "base cannot be null!");
        return new ItemFactory(base);
    }

    /**
     * Creates a new ItemFactory for an item of the given material
     * 
     * @param type The material of the item
     * @return The new {@link ItemFactory}
     */
    public static ItemFactory of(Material type) {
        Objects.requireNonNull(type, "type cannot be null!");
        return of(new ItemStack(type));
    }

    /**
     * Sets the type of the item
     * 
     * @param type The new item type to use
     * @return This {@link ItemFactory} instance
     */
    public ItemFactory setMaterial(Material type) {
        base.setType(type);
        return this;
    }

    /**
     * Sets the type of the item
     * 
     * @param type The new item type to use
     * @return This {@link ItemFactory} instance
     */
    public ItemFactory setType(Material type) {
        return setMaterial(type);
    }

    /**
     * Sets the amount of this item
     * 
     * @param amount The amount to set
     * @return This {@link ItemFactory} instance
     */
    public ItemFactory setAmount(int amount) {
        base.setAmount(amount);
        return this;
    }

    /**
     * Sets the amount of this item
     * 
     * @param amount The amount to set
     * @return This {@link ItemFactory} instance
     */
    public ItemFactory setCount(int amount) {
        return setAmount(amount);
    }

    /**
     * Sets the durability of this item
     * 
     * @param durability The durability to set
     * @return This {@link ItemFactory} instance
     */
    public ItemFactory setDurability(int durability) {
        base.setDurability((short) durability);
        return this;
    }

    /**
     * Sets the durability of this item
     * 
     * @param damage The durability to set
     * @return This {@link ItemFactory} instance
     */
    public ItemFactory setDamage(int damage) {
        return setDurability(damage);
    }

    /**
     * Gets the {@link ItemMeta} of this item
     * 
     * @return The item's meta
     */
    public ItemMeta getItemMeta() {
        return base.getItemMeta();
    }

    /**
     * Sets the {@link ItemMeta} of this item
     * 
     * @param meta The new {@link ItemMeta} to use
     * @return This {@link ItemFactory} instance
     */
    public ItemFactory setItemMeta(ItemMeta meta) {
        this.base.setItemMeta(meta);
        return this;
    }

    /**
     * Sets the display name of this item (converts color/formatting codes too)
     * 
     * @param name The name to set
     * @return This {@link ItemFactory} instance
     */
    public ItemFactory setDisplayName(String name) {
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(TextUtils.colorize(name));
        return setItemMeta(meta);
    }

    /**
     * Sets the display name of this item (converts color/formatting codes too)
     * 
     * @param name The name to set
     * @return This {@link ItemFactory} instance
     */
    public ItemFactory setName(String name) {
        return setDisplayName(name);
    }

    /**
     * Sets the lore of this item (converts color/formatting codes too)
     * 
     * @param lore The lore to set
     * @return This {@link ItemFactory} instance
     */
    public ItemFactory setLore(List<String> lore) {
        ItemMeta meta = getItemMeta();
        meta.setLore(TextUtils.colorize(lore));
        return setItemMeta(meta);
    }

    /**
     * Sets the lore of this item (converts color/formatting codes too)
     * 
     * @param lore The lore to set
     * @return This {@link ItemFactory} instance
     */
    public ItemFactory setLore(String... lore) {
        // TODO: 1/25/17 - Better way to make a mutable List from an array?
        return setLore(Arrays.asList(lore));
    }

    /**
     * Gets the lore of this item. WARNING: This behaves differently from {@link ItemMeta#getLore()}! If {@link ItemMeta#hasLore()} returns <code>false</code>, this <em>will not error</em>. Instead, an empty list will be returned.
     * 
     * @return The item's lore
     */
    public List<String> getLore() {
        ItemMeta meta = getItemMeta();
        return meta.hasLore() ? meta.getLore() : new ArrayList<String>();
    }

    /**
     * Adds one or more lines of lore to this item
     * 
     * @param lore The lore to add
     * @return This {@link ItemFactory} instance
     */
    public ItemFactory addLore(List<String> lore) {
        List<String> newLore = getLore();
        newLore.addAll(lore);
        return setLore(newLore);
    }

    /**
     * Adds one or more lines of lore to this item
     * 
     * @param lore The lore to add
     * @return This {@link ItemFactory} instance
     */
    public ItemFactory addLore(String... lore) {
        return addLore(Arrays.asList(lore));
    }

    /**
     * @return The completed {@link ItemStack}
     */
    public ItemStack build() {
        return base.clone();
    }

}
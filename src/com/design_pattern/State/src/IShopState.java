package com.design_pattern.State.src;
/**
 *  A state interface
 *  �û������Ϲ����״̬�仯:
 *  ѡ����Ʒ --> ���ɶ��� --> ����ȡ��
 */
public interface IShopState {
    public void shop();
    public void generateBill();
    public void pay();
    //protected void changeState(ShopContext c, IShopState);
}

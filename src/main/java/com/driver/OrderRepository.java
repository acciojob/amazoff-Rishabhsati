package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private HashMap<String, Order> orderMap;
    private HashMap<String, DeliveryPartner> partnerMap;
    private HashMap<String, HashSet<String>> partnerToOrderMap;
    private HashMap<String, String> orderToPartnerMap;

    public OrderRepository(){
        this.orderMap = new HashMap<String, Order>();
        this.partnerMap = new HashMap<String, DeliveryPartner>();
        this.partnerToOrderMap = new HashMap<String, HashSet<String>>();
        this.orderToPartnerMap = new HashMap<String, String>();
    }

    public void saveOrder(Order order){
        // your code here
        String orderId = order.getId();
        orderMap.put(orderId,order);
    }

    public void savePartner(String partnerId){
        // your code here
        // create a new partner with given partnerId and save it
        DeliveryPartner partner = new DeliveryPartner(partnerId);
        partnerMap.put(partnerId,partner);
    }

    public void saveOrderPartnerMap(String orderId, String partnerId){
        if(orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)){
            // your code here

            //add order to given partner's order list

            HashSet<String> partnerOrderList = partnerToOrderMap.get(partnerId);
            partnerOrderList.add(orderId);
            partnerToOrderMap.put(partnerId,partnerOrderList);

            //increase order count of partner


            //assign partner to this order
            orderToPartnerMap.put(orderId,partnerId);
        }
    }

    public Order findOrderById(String orderId){
        // your code here
        Order currOrder = null;
        for(String order : orderMap.keySet()){
            if(order.equals(orderId)) currOrder= orderMap.get(order);
        }
        return currOrder;
    }

    public DeliveryPartner findPartnerById(String partnerId){
        // your code here
        DeliveryPartner currPartner = null;
        for(String partner : partnerMap.keySet()){
            if(partner.equals(partnerId)) currPartner= partnerMap.get(currPartner);
        }
        return currPartner;
    }

    public Integer findOrderCountByPartnerId(String partnerId){
        // your code here
        Integer size = null;
        HashSet<String> set = partnerToOrderMap.get(partnerId);
        size = set.size();
        return size;
    }

    public List<String> findOrdersByPartnerId(String partnerId){
        // your code here
        List<String> list = null;
        HashSet<String> set = partnerToOrderMap.get(partnerId);
        for(String str : set) {
            list.add(str);
        }
        return list;
    }

    public List<String> findAllOrders(){
        // your code here
        List<String> list = null;
        list.addAll(orderMap.keySet());
        return list;
        // return list of all orders
    }

    public void deletePartner(String partnerId){
        // your code here
        // delete partner by ID
        for(String partner : partnerMap.keySet()){
            if(partner.equals(partnerId)){
                partnerMap.remove(partnerId);
            }
        }
    }

    public void deleteOrder(String orderId){
        // your code here
        // delete order by ID
        for(String order : orderMap.keySet()){
            if(order.equals(orderId)){
                partnerMap.remove(orderId);
            }
        }
    }

    public Integer findCountOfUnassignedOrders(){
        // your code here
    }

    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String timeString, String partnerId){
        // your code here
    }

    public String findLastDeliveryTimeByPartnerId(String partnerId){
        // your code here
        // code should return string in format HH:MM
    }
}
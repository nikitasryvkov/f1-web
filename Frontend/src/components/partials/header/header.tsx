import { Layout, Menu } from "antd";
import { FC } from "react";
import Title from "antd/es/typography/Title";

const { Header, } = Layout;

export const HeaderC: FC = () => {
  return (
  <Header color="red" style={{ display: 'flex', alignItems: 'center', backgroundColor: "#cf1322",  justifyContent: "center"}}>
    <Title style={{color: "white", fontFamily: "Comic Sans MS, Comic Sans, cursive", margin: 0}}>F1 Managment</Title>
    <Menu
      // mode="horizontal"
      // defaultSelectedKeys={['2']}
      // style={{ flex: 1, minWidth: 0 }}
    />
  </Header>
  );
};

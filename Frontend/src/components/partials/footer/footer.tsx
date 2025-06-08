import { Layout } from "antd";
import { FC } from "react";

const { Footer } = Layout;

export const FooterС: FC = () => {
  return (
    <Footer style={{ textAlign: "center" }}>
      Formula 1 Management ©{new Date().getFullYear()} Created by Nikita Sryvkov
    </Footer>
  );
};

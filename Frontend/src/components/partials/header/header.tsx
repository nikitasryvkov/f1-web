import { Layout  } from "antd";
import { FC, useEffect, useState } from "react";
import Title from "antd/es/typography/Title";

const { Header } = Layout;

export const HeaderC: FC = () => {
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);

  useEffect(() => {
    const handleResize = () => {
      setWindowWidth(window.innerWidth);
    };

    window.addEventListener('resize', handleResize);

    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []);

  return (
  <Header color="red" style={{ display: 'flex', alignItems: 'center', backgroundColor: "#cf1322",  justifyContent: "center", wordWrap: "break-word", overflowWrap: "break-word", padding: windowWidth < 768 ? '10px' : '20px'}}>
    <Title style={{color: "white", fontFamily: "Comic Sans MS, Comic Sans, cursive", margin: 0, fontSize: windowWidth < 768 ? '1.5rem' : '2rem'}}>F1 Managment</Title>
  </Header>
  );
};

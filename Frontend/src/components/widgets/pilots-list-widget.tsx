import { FC } from "react";
import { PilotCard } from "../ui/pilot/pilot-card";
import { PilotDto } from "../../dtos/pilot-dto";
import { PilotStatus } from "../../entities/pilotStatus";
import Title from "antd/es/typography/Title";
import { Col, Row } from "antd";

export interface IActivePilotsWidgets {
  lable?: string;
  pilots: PilotDto[];
  onUpdate?: (pilot: PilotDto) => void;
  onDelete?: (id: string) => void;
  onPosition?: (id: string, status: PilotStatus) => void;
}

export const PilotListWidget: FC<IActivePilotsWidgets> = ({
  pilots,
  lable,
  onUpdate,
  onDelete,
  onPosition,
}) => {
  const onBlockUpdate = (id: string) => {
    const pilot = pilots.find((p) => p.id === id);
    if (pilot) {
      onUpdate?.(pilot);
    }
  };

  const onPositionState = (id: string, status: PilotStatus) => {
    const pilot = pilots.find((p) => p.id === id);
    if (pilot) {
      onPosition?.(id, status);
    }
  };

    const onDeletePilot = (id: string) => {
    const pilot = pilots.find((p) => p.id === id);
    if (pilot) {
      onDelete?.(id);
    }
  };

  // return (
  //   <>
  //     <div style={{display: "grid", gridTemplateColumns: "repeat(auto-fill, minmax(280px, 1fr))", gap: 16}}>
  //       {lable && <Title>{lable}</Title>}
  //     </div>

  //     {pilots.map((p) => (
  //       <PilotCard
  //         pilot={p}
  //         onStateChange={onBlockUpdate}
  //         onPosition={onPositionState}
  //         key={p.id}
  //       />
  //     ))}
  //   </>
    return (
    <div >
      {lable && <Title level={3}>{lable}</Title>}
      
        <Row gutter={[16, 16]} style={{ marginTop: 16 }}>
          {pilots.map((p) => (
            <Col key={p.id} xs={24} sm={12} md={8} lg={6}>
              <PilotCard
                pilot={p}
                onStateChange={onBlockUpdate}
                onDeletePilot={onDeletePilot}
                onPosition={onPositionState}
              />
            </Col>
          ))}
        </Row>
    </div>
  );
};

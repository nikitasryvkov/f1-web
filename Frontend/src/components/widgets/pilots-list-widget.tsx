import { FC } from "react";
// import { Pilot } from "../../entities/pilot";
import { PilotCard } from "../ui/pilot/pilot-card";
import { PilotDto } from "../../dtos/pilot-dto";
import { PilotStatus } from "../../entities/pilotStatus";
import Title from "antd/es/typography/Title";
// import { Col } from "antd";

export interface IActivePilotsWidgets {
  lable?: string;
  pilots: PilotDto[];
  onUpdate?: (pilot: PilotDto) => void;
  onPosition?: (id: string, status: PilotStatus) => void;
}

export const PilotListWidget: FC<IActivePilotsWidgets> = ({
  pilots,
  lable,
  onUpdate,
  onPosition,
}) => {
  const onBlockUpdate = (id: string) => {
    onUpdate?.(pilots.find((p) => p.id === id)!);
  };

  const onPositionState = (id: string, status: PilotStatus) => {
    const pilot = pilots.find((p) => p.id === id);
    if (pilot) {
      onPosition?.(id, status);
    }
  };

  return (
    <>
      <Title
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(auto-fill, minmax(280px, 1fr))",
          gap: 16,
        }}
      >
        {lable}
      </Title>

      {pilots.map((p) => (
        <PilotCard
          pilot={p}
          onStateChange={onBlockUpdate}
          onPosition={onPositionState}
          key={p.id}
        />
      ))}
    </>
  );
};

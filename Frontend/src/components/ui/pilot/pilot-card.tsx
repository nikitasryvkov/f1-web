import { FC } from "react";
import { PilotStatus } from "../../../entities/pilotStatus";
import { PilotDto } from "../../../dtos/pilot-dto";
import { Button, Card, Divider, Select, Space } from "antd";

export interface IPilotCardProps {
  pilot: PilotDto;
  onStateChange?: (id: string) => void;
  onDeletePilot?: (id: string) => void;
  onPosition?: (id: string, status: PilotStatus) => void;
}

export const PilotCard: FC<IPilotCardProps> = ({
  pilot,
  onStateChange,
  onDeletePilot,
  onPosition
}) => {
  return (
    <Space>
      <Card
        style={{
          backgroundColor: "#ffccc7",
          textAlign: "center",
          height: '100%',
          display: 'flex',
          flexDirection: 'column',
          justifyContent: 'space-between',
          // width: "100%",
          // maxWidth: "250px",
          // minWidth: "250px",
          // margin: "0 auto",
        }}
        title={pilot.team.title}
      >
        <p style={{ margin: "4px 0" }}>
          {pilot.firstName} {pilot.secondName} {pilot.number}
        </p>
        <p style={{ margin: "4px 0" }}>
          <PilotStatusCard status={pilot.status} />{" "}
        </p>
        <p style={{ margin: "4px 0" }}>
          {pilot.blocked ? "Blocked" : "Not blocked"}
        </p>
        <p style={{ margin: "4px 0" }}>{pilot.country.title}</p>

        <Divider style={{ borderColor: "red" }}>Change</Divider>

        <div
          style={{
            display: "flex",
            flexWrap: "wrap",
            gap: 8,
            justifyContent: "center",
          }}
        >
          <Button onClick={() => onDeletePilot?.(pilot.id)} style={{maxWidth: "60px"}}>
            {"Delete"}
          </Button>
          <Button onClick={() => onStateChange?.(pilot.id)} style={{maxWidth: "60px"}}>
            {pilot.blocked ? "Unblock" : "Block"}
          </Button>
          <Select
            value={pilot.status}
            onChange={(e) => onPosition?.(pilot.id, e as PilotStatus)}
            style={{ width: 120 }}
            disabled={pilot.blocked === true}
          >
            {Object.values(PilotStatus).map((status) => (
              <Select.Option key={status} value={status}>
                {status}
              </Select.Option>
            ))}
          </Select>
        </div>
      </Card>
    </Space>
  );
};

export interface IPilotStatusCardProps {
  status: PilotStatus;
}

export const PilotStatusCard: FC<IPilotStatusCardProps> = ({ status }) => {
  const statusToTextMapping: Record<PilotStatus, string> = {
    [PilotStatus.FIRST]: "First",
    [PilotStatus.SECOND]: "Second",
    [PilotStatus.RESERVE]: "Reserve",
  };

  return <>{statusToTextMapping[status]}</>;
};

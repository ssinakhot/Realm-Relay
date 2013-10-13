package realmrelay.packets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


import realmrelay.data.IData;
import realmrelay.packets.client.AOEAckPacket;
import realmrelay.packets.client.AcceptTradePacket;
import realmrelay.packets.client.BuyPacket;
import realmrelay.packets.client.CancelTradePacket;
import realmrelay.packets.client.ChangeGuildRankPacket;
import realmrelay.packets.client.ChangeTradePacket;
import realmrelay.packets.client.CheckCreditsPacket;
import realmrelay.packets.client.ChooseNamePacket;
import realmrelay.packets.client.CreateGuildPacket;
import realmrelay.packets.client.CreatePacket;
import realmrelay.packets.client.EditAccountListPacket;
import realmrelay.packets.client.EnemyHitPacket;
import realmrelay.packets.client.EscapePacket;
import realmrelay.packets.client.GoToAckPacket;
import realmrelay.packets.client.GroundDamagePacket;
import realmrelay.packets.client.GuildInvitePacket;
import realmrelay.packets.client.GuildRemovePacket;
import realmrelay.packets.client.HelloPacket;
import realmrelay.packets.client.InvDropPacket;
import realmrelay.packets.client.InvSwapPacket;
import realmrelay.packets.client.JoinGuildPacket;
import realmrelay.packets.client.LoadPacket;
import realmrelay.packets.client.MovePacket;
import realmrelay.packets.client.OtherHitPacket;
import realmrelay.packets.client.PlayerHitPacket;
import realmrelay.packets.client.PlayerShootPacket;
import realmrelay.packets.client.PlayerTextPacket;
import realmrelay.packets.client.PongPacket;
import realmrelay.packets.client.RequestTradePacket;
import realmrelay.packets.client.ReskinPacket;
import realmrelay.packets.client.SetConditionPacket;
import realmrelay.packets.client.ShootAckPacket;
import realmrelay.packets.client.SquareHitPacket;
import realmrelay.packets.client.TeleportPacket;
import realmrelay.packets.client.UpdateAckPacket;
import realmrelay.packets.client.UseItemPacket;
import realmrelay.packets.client.UsePortalPacket;
import realmrelay.packets.server.AOEPacket;
import realmrelay.packets.server.AccountListPacket;
import realmrelay.packets.server.AllyShootPacket;
import realmrelay.packets.server.BuyResultPacket;
import realmrelay.packets.server.ClientStatPacket;
import realmrelay.packets.server.Create_SuccessPacket;
import realmrelay.packets.server.DamagePacket;
import realmrelay.packets.server.DeathPacket;
import realmrelay.packets.server.FailurePacket;
import realmrelay.packets.server.Global_NotificationPacket;
import realmrelay.packets.server.GoToPacket;
import realmrelay.packets.server.InvResultPacket;
import realmrelay.packets.server.InvitedToGuildPacket;
import realmrelay.packets.server.MapInfoPacket;
import realmrelay.packets.server.NameResultPacket;
import realmrelay.packets.server.New_TickPacket;
import realmrelay.packets.server.NotificationPacket;
import realmrelay.packets.server.PicPacket;
import realmrelay.packets.server.PingPacket;
import realmrelay.packets.server.PlaySoundPacket;
import realmrelay.packets.server.QuestObjIdPacket;
import realmrelay.packets.server.ReconnectPacket;
import realmrelay.packets.server.Shoot2Packet;
import realmrelay.packets.server.ShootPacket;
import realmrelay.packets.server.Show_EffectPacket;
import realmrelay.packets.server.TextPacket;
import realmrelay.packets.server.TradeAcceptedPacket;
import realmrelay.packets.server.TradeChangedPacket;
import realmrelay.packets.server.TradeDonePacket;
import realmrelay.packets.server.TradeRequestedPacket;
import realmrelay.packets.server.TradeStartPacket;
import realmrelay.packets.server.UpdatePacket;


public abstract class Packet implements IData {
	
	/**
	 * Creates new packet from packet id
	 * @param id
	 * @return
	 */
	public static Packet create(byte id) {
		switch (id) {
			case AcceptTradePacket.ID: { return new AcceptTradePacket(); }
			case AccountListPacket.ID: { return new AccountListPacket(); }
			case AllyShootPacket.ID: { return new AllyShootPacket(); }
			case AOEAckPacket.ID: { return new AOEAckPacket(); }
			case AOEPacket.ID: { return new AOEPacket(); }
			case BuyPacket.ID: { return new BuyPacket(); }
			case BuyResultPacket.ID: { return new BuyResultPacket(); }
			case CancelTradePacket.ID: { return new CancelTradePacket(); }
			case ChangeGuildRankPacket.ID: { return new ChangeGuildRankPacket(); }
			case ChangeTradePacket.ID: { return new ChangeTradePacket(); }
			case CheckCreditsPacket.ID: { return new CheckCreditsPacket(); }
			case ChooseNamePacket.ID: { return new ChooseNamePacket(); }
			case ClientStatPacket.ID: { return new ClientStatPacket(); }
			case Create_SuccessPacket.ID: { return new Create_SuccessPacket(); }
			case CreateGuildPacket.ID: { return new CreateGuildPacket(); }
			case CreatePacket.ID: { return new CreatePacket(); }
			case DamagePacket.ID: { return new DamagePacket(); }
			case DeathPacket.ID: { return new DeathPacket(); }
			case EditAccountListPacket.ID: { return new EditAccountListPacket(); }
			case EnemyHitPacket.ID: { return new EnemyHitPacket(); }
			case EscapePacket.ID: { return new EscapePacket(); }
			case FailurePacket.ID: { return new FailurePacket(); }
			case Global_NotificationPacket.ID: { return new Global_NotificationPacket(); }
			case GoToAckPacket.ID: { return new GoToAckPacket(); }
			case GoToPacket.ID: { return new GoToPacket(); }
			case GroundDamagePacket.ID: { return new GroundDamagePacket(); }
			case GuildInvitePacket.ID: { return new GuildInvitePacket(); }
			case GuildRemovePacket.ID: { return new GuildRemovePacket(); }
			case HelloPacket.ID: { return new HelloPacket(); }
			case InvDropPacket.ID: { return new InvDropPacket(); }
			case InvitedToGuildPacket.ID: { return new InvitedToGuildPacket(); }
			case InvResultPacket.ID: { return new InvResultPacket(); }
			case InvSwapPacket.ID: { return new InvSwapPacket(); }
			case JoinGuildPacket.ID: { return new JoinGuildPacket(); }
			case LoadPacket.ID: { return new LoadPacket(); }
			case MapInfoPacket.ID: { return new MapInfoPacket(); }
			case MovePacket.ID: { return new MovePacket(); }
			case NameResultPacket.ID: { return new NameResultPacket(); }
			case New_TickPacket.ID: { return new New_TickPacket(); }
			case NotificationPacket.ID: { return new NotificationPacket(); }
			case OtherHitPacket.ID: { return new OtherHitPacket(); }
			case PicPacket.ID: { return new PicPacket(); }
			case PingPacket.ID: { return new PingPacket(); }
			case PlayerHitPacket.ID: { return new PlayerHitPacket(); }
			case PlayerShootPacket.ID: { return new PlayerShootPacket(); }
			case PlayerTextPacket.ID: { return new PlayerTextPacket(); }
			case PlaySoundPacket.ID: { return new PlaySoundPacket(); }
			case PongPacket.ID: { return new PongPacket(); }
			case QuestObjIdPacket.ID: { return new QuestObjIdPacket(); }
			case ReconnectPacket.ID: { return new ReconnectPacket(); }
			case RequestTradePacket.ID: { return new RequestTradePacket(); }
			case ReskinPacket.ID: { return new ReskinPacket(); }
			case SetConditionPacket.ID: { return new SetConditionPacket(); }
			case Shoot2Packet.ID: { return new Shoot2Packet(); }
			case ShootAckPacket.ID: { return new ShootAckPacket(); }
			case ShootPacket.ID: { return new ShootPacket(); }
			case Show_EffectPacket.ID: { return new Show_EffectPacket(); }
			case SquareHitPacket.ID: { return new SquareHitPacket(); }
			case TeleportPacket.ID: { return new TeleportPacket(); }
			case TextPacket.ID: { return new TextPacket(); }
			case TradeAcceptedPacket.ID: { return new TradeAcceptedPacket(); }
			case TradeChangedPacket.ID: { return new TradeChangedPacket(); }
			case TradeDonePacket.ID: { return new TradeDonePacket(); }
			case TradeRequestedPacket.ID: { return new TradeRequestedPacket(); }
			case TradeStartPacket.ID: { return new TradeStartPacket(); }
			case UpdateAckPacket.ID: { return new UpdateAckPacket(); }
			case UpdatePacket.ID: { return new UpdatePacket(); }
			case UseItemPacket.ID: { return new UseItemPacket(); }
			case UsePortalPacket.ID: { return new UsePortalPacket(); }
		}
		return new UnknownPacket(id);
	}
	
	/**
	 * Creates new packet from packet id and packet bytes
	 * @param id
	 * @param bytes
	 * @return
	 * @throws IOException
	 */
	public static Packet create(byte id, byte[] bytes) throws IOException {
		Packet packet = Packet.create(id);
		packet.parseFromInput(new DataInputStream(new ByteArrayInputStream(bytes)));
		return packet;
	}
	
	public byte[] getBytes() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(baos);
		this.writeToOutput(out);
		return baos.toByteArray();
	}
	
	/**
	 * Returns the byte id of the packet
	 * @return
	 */
	public abstract byte id();
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + this.id();
	}

}

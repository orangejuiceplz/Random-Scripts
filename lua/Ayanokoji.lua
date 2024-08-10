local ReplicatedStorage = game:GetService("ReplicatedStorage")
local Players = game:GetService("Players")
local SoundService = game:GetService("SoundService")

print("AyanokojiAwakening server script starting...")

-- Create or get RemoteEvents
local AwakeningEvent = ReplicatedStorage:FindFirstChild("AwakeningEvent") or Instance.new("RemoteEvent")
AwakeningEvent.Name = "AwakeningEvent"
AwakeningEvent.Parent = ReplicatedStorage

local TacticalAnalysisEvent = ReplicatedStorage:FindFirstChild("TacticalAnalysisEvent") or Instance.new("RemoteEvent")
TacticalAnalysisEvent.Name = "TacticalAnalysisEvent"
TacticalAnalysisEvent.Parent = ReplicatedStorage

local PsychologicalManipulationEvent = ReplicatedStorage:FindFirstChild("PsychologicalManipulationEvent") or Instance.new("RemoteEvent")
PsychologicalManipulationEvent.Name = "PsychologicalManipulationEvent"
PsychologicalManipulationEvent.Parent = ReplicatedStorage

local ReverseControlsEvent = ReplicatedStorage:FindFirstChild("ReverseControlsEvent") or Instance.new("RemoteEvent")
ReverseControlsEvent.Name = "ReverseControlsEvent"
ReverseControlsEvent.Parent = ReplicatedStorage

local RapidAdaptationEvent = ReplicatedStorage:FindFirstChild("RapidAdaptationEvent") or Instance.new("RemoteEvent")
RapidAdaptationEvent.Name = "RapidAdaptationEvent"
RapidAdaptationEvent.Parent = ReplicatedStorage

-- Game constants
local AwakeningCooldown = 60 -- Cooldown in seconds
local AwakeningDuration = 30 -- Duration in seconds
local TacticalAnalysisCooldown = 15
local PsychologicalManipulationCooldown = 20
local RapidAdaptationCooldown = 25

-- Player data storage
local PlayerAwakenings = {}
local PlayerAbilityCooldowns = {}

-- Table of all sound IDs used in the game
local soundIds = {
	["Awakening"] = "rbxassetid://18882534565",
	["TacticalAnalysis"] = "rbxassetid://18882263215",
	["PsychologicalManipulation"] = "rbxassetid://18882251207",
	-- Add any other sound IDs used in your game here
}

-- Function to preload a single sound
local function preloadSound(soundId)
	local sound = Instance.new("Sound")
	sound.SoundId = soundId
	sound.Parent = SoundService
	sound:Play()
	sound.Ended:Wait()
	sound:Destroy()
end

-- Function to preload all sounds for a player
local function preloadAllSounds(player)
	local preloadFolder = Instance.new("Folder")
	preloadFolder.Name = "PreloadedSounds"
	preloadFolder.Parent = player

	for _, soundId in pairs(soundIds) do
		local sound = Instance.new("Sound")
		sound.SoundId = soundId
		sound.Volume = 0  -- Set volume to 0 to preload silently
		sound.Parent = preloadFolder
		sound:Play()
	end

	-- Wait for all sounds to finish preloading
	while #preloadFolder:GetChildren() > 0 do
		for _, sound in ipairs(preloadFolder:GetChildren()) do
			if sound.IsLoaded then
				sound:Destroy()
			end
		end
		task.wait(0.1)
	end

	preloadFolder:Destroy()
	print("All sounds preloaded for " .. player.Name)
end

-- Function to create particle effect
local function createParticleEffect(parent, color, size, lifetime)
	local effect = Instance.new("ParticleEmitter")
	effect.Color = ColorSequence.new(color)
	effect.Size = NumberSequence.new(size)
	effect.Lifetime = NumberRange.new(lifetime)
	effect.Rate = 50
	effect.Speed = NumberRange.new(3, 5)
	effect.SpreadAngle = Vector2.new(0, 360)
	effect.Parent = parent
	return effect
end

-- Function to play sound effect
local function playSoundEffect(parent, soundId, volume)
	local sound = Instance.new("Sound")
	sound.SoundId = soundId
	sound.Volume = volume
	sound.Parent = parent
	sound:Play()
	game.Debris:AddItem(sound, sound.TimeLength)
end

-- Function to check and set ability cooldown
local function checkAndSetCooldown(player, abilityName, cooldownTime)
	if not PlayerAbilityCooldowns[player] then
		PlayerAbilityCooldowns[player] = {}
	end
	if PlayerAbilityCooldowns[player][abilityName] and os.time() - PlayerAbilityCooldowns[player][abilityName] < cooldownTime then
		return false
	end

	PlayerAbilityCooldowns[player][abilityName] = os.time()
	return true
end

-- Function to check if player is awakened
local function isPlayerAwakened(player)
	return PlayerAwakenings[player] and os.time() - PlayerAwakenings[player] < AwakeningDuration
end

local function activateAwakening(player)
	local character = player.Character
	if not character then return end
	local humanoid = character:FindFirstChild("Humanoid")
	if not humanoid then return end

	if not checkAndSetCooldown(player, "Awakening", AwakeningCooldown) then
		return
	end

	PlayerAwakenings[player] = os.time()

	-- Awakening aura
	local aura = createParticleEffect(character.HumanoidRootPart, Color3.new(0.5, 0, 1), 0.5, 1)

	-- Awakening sound
	playSoundEffect(character.HumanoidRootPart, soundIds["Awakening"], 0.5)

	local originalWalkSpeed = humanoid.WalkSpeed
	local originalJumpPower = humanoid.JumpPower
	humanoid.WalkSpeed = humanoid.WalkSpeed * 1.3
	humanoid.JumpPower = humanoid.JumpPower * 1.2

	task.delay(AwakeningDuration, function()
		aura:Destroy()
		humanoid.WalkSpeed = originalWalkSpeed
		humanoid.JumpPower = originalJumpPower

		-- Deactivation effect
		local deactivationEffect = createParticleEffect(character.HumanoidRootPart, Color3.new(1, 1, 1), 1, 0.5)
		game.Debris:AddItem(deactivationEffect, 0.5)

		-- Deactivation sound
		playSoundEffect(character.HumanoidRootPart, soundIds["Awakening"], 0.3)

		PlayerAwakenings[player] = nil
	end)

	print(player.Name .. " activated awakening")
end

local function tacticalAnalysis(player)
	if not isPlayerAwakened(player) then
		print(player.Name .. " tried to use Tactical Analysis without being awakened")
		return
	end

	if not checkAndSetCooldown(player, "TacticalAnalysis", TacticalAnalysisCooldown) then
		return
	end

	print("Tactical Analysis activated for " .. player.Name)

	local revealRadius = 50
	local revealDuration = 5

	-- Tactical Analysis activation effect
	local analysisEffect = createParticleEffect(player.Character.HumanoidRootPart, Color3.new(0, 1, 1), 0.3, 1)
	game.Debris:AddItem(analysisEffect, 1)

	-- Tactical Analysis sound
	playSoundEffect(player.Character.HumanoidRootPart, soundIds["TacticalAnalysis"], 0.4)

	for _, otherPlayer in ipairs(Players:GetPlayers()) do
		if otherPlayer ~= player and otherPlayer.Character then
			local distance = (otherPlayer.Character.PrimaryPart.Position - player.Character.PrimaryPart.Position).Magnitude
			if distance <= revealRadius then
				local highlight = Instance.new("Highlight")
				highlight.FillColor = Color3.new(1, 0, 0)
				highlight.OutlineColor = Color3.new(1, 0, 0)
				highlight.Parent = otherPlayer.Character

				-- Show health bar above highlighted players
				local healthBar = Instance.new("BillboardGui")
				healthBar.Size = UDim2.new(2, 0, 0.5, 0)
				healthBar.StudsOffset = Vector3.new(0, 3, 0)
				healthBar.Parent = otherPlayer.Character.Head

				local fill = Instance.new("Frame")
				fill.Size = UDim2.new(otherPlayer.Character.Humanoid.Health / otherPlayer.Character.Humanoid.MaxHealth, 0, 1, 0)
				fill.BackgroundColor3 = Color3.new(0, 1, 0)
				fill.Parent = healthBar

				task.delay(revealDuration, function()
					highlight:Destroy()
					healthBar:Destroy()
				end)
			end
		end
	end
end

local function psychologicalManipulation(player)
	if not isPlayerAwakened(player) then
		print(player.Name .. " tried to use Psychological Manipulation without being awakened")
		return
	end

	if not checkAndSetCooldown(player, "PsychologicalManipulation", PsychologicalManipulationCooldown) then
		return
	end

	print("Psychological Manipulation activated for " .. player.Name)

	local confuseRadius = 20
	local confuseDuration = 3

	-- Psychological Manipulation activation effect
	local manipulationEffect = createParticleEffect(player.Character.HumanoidRootPart, Color3.new(1, 0, 1), 0.3, 1)
	game.Debris:AddItem(manipulationEffect, 1)

	-- Psychological Manipulation sound
	playSoundEffect(player.Character.HumanoidRootPart, soundIds["PsychologicalManipulation"], 0.4)

	for _, otherPlayer in ipairs(Players:GetPlayers()) do
		if otherPlayer ~= player and otherPlayer.Character then
			local distance = (otherPlayer.Character.PrimaryPart.Position - player.Character.PrimaryPart.Position).Magnitude
			if distance <= confuseRadius then
				ReverseControlsEvent:FireClient(otherPlayer, true)

				-- Confusion effect on affected player
				local confusionEffect = createParticleEffect(otherPlayer.Character.Head, Color3.new(1, 0, 1), 0.2, confuseDuration)
				game.Debris:AddItem(confusionEffect, confuseDuration)

				-- Slow down affected players
				local humanoid = otherPlayer.Character:FindFirstChild("Humanoid")
				if humanoid then
					local originalWalkSpeed = humanoid.WalkSpeed
					humanoid.WalkSpeed = originalWalkSpeed * 0.5

					task.delay(confuseDuration, function()
						ReverseControlsEvent:FireClient(otherPlayer, false)
						humanoid.WalkSpeed = originalWalkSpeed
					end)
				end
			end
		end
	end
end

local function rapidAdaptation(player)
	if not isPlayerAwakened(player) then
		print(player.Name .. " tried to use Rapid Adaptation without being awakened")
		return
	end

	if not checkAndSetCooldown(player, "RapidAdaptation", RapidAdaptationCooldown) then
		return
	end

	print("Rapid Adaptation activated for " .. player.Name)

	local adaptationDuration = 10
	local adaptationRadius = 30

	-- Rapid Adaptation activation effect
	local adaptationEffect = createParticleEffect(player.Character.HumanoidRootPart, Color3.new(0, 1, 0), 0.3, 1)
	game.Debris:AddItem(adaptationEffect, 1)

	-- Rapid Adaptation sound
	playSoundEffect(player.Character.HumanoidRootPart, soundIds["Awakening"], 0.4)

	-- Create a force field around the player
	local forceField = Instance.new("ForceField")
	forceField.Parent = player.Character

	-- Increase player's stats
	local humanoid = player.Character:FindFirstChild("Humanoid")
	if humanoid then
		local originalWalkSpeed = humanoid.WalkSpeed
		local originalJumpPower = humanoid.JumpPower
		humanoid.WalkSpeed = originalWalkSpeed * 1.5
		humanoid.JumpPower = originalJumpPower * 1.3

		-- Create an area of effect that slows down enemies
		local slowArea = Instance.new("Part")
		slowArea.Size = Vector3.new(adaptationRadius * 2, 1, adaptationRadius * 2)
		slowArea.CFrame = player.Character.HumanoidRootPart.CFrame * CFrame.new(0, -2, 0)
		slowArea.Anchored = true
		slowArea.CanCollide = false
		slowArea.Transparency = 0.8
		slowArea.BrickColor = BrickColor.new("Lime green")
		slowArea.Shape = Enum.PartType.Cylinder
		slowArea.Parent = workspace

		-- Slow down enemies in the area
		local slowConnection
		slowConnection = game:GetService("RunService").Heartbeat:Connect(function()
			for _, otherPlayer in ipairs(Players:GetPlayers()) do
				if otherPlayer ~= player and otherPlayer.Character then
					local distance = (otherPlayer.Character.PrimaryPart.Position - player.Character.PrimaryPart.Position).Magnitude
					if distance <= adaptationRadius then
						local otherHumanoid = otherPlayer.Character:FindFirstChild("Humanoid")
						if otherHumanoid then
							otherHumanoid.WalkSpeed = otherHumanoid.WalkSpeed * 0.8
						end
					end
				end
			end
		end)

		task.delay(adaptationDuration, function()
			forceField:Destroy()
			humanoid.WalkSpeed = originalWalkSpeed
			humanoid.JumpPower = originalJumpPower
			slowArea:Destroy()
			slowConnection:Disconnect()
		end)
	end
end

-- Preload sounds for each player when they join
Players.PlayerAdded:Connect(function(player)
	print(player.Name .. " joined. Preloading sounds...")
	preloadAllSounds(player)
end)

-- Connect events to their respective functions
AwakeningEvent.OnServerEvent:Connect(activateAwakening)
TacticalAnalysisEvent.OnServerEvent:Connect(tacticalAnalysis)
PsychologicalManipulationEvent.OnServerEvent:Connect(psychologicalManipulation)
RapidAdaptationEvent.OnServerEvent:Connect(rapidAdaptation)

print("AyanokojiAwakening script setup complete")

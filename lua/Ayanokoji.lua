local Players = game:GetService("Players")
local ReplicatedStorage = game:GetService("ReplicatedStorage")

local AwakeningEvent = ReplicatedStorage:WaitForChild("AwakeningEvent")
local CooldownTime = 60 

local PlayerAwakenings = {}

local function activateAwakening(player)
    local character = player.Character
    if not character then return end
    
    local humanoid = character:FindFirstChild("Humanoid")
    if not humanoid then return end
    
    if PlayerAwakenings[player] and os.time() - PlayerAwakenings[player] < CooldownTime then
        return
    end
    
    PlayerAwakenings[player] = os.time()
    
  
    local aura = Instance.new("ParticleEmitter")
    aura.Color = ColorSequence.new(Color3.new(0.5, 0, 1)) -- Purple aura
    aura.Parent = character.HumanoidRootPart
    
    humanoid.WalkSpeed = humanoid.WalkSpeed * 1.3
    humanoid.JumpPower = humanoid.JumpPower * 1.2
    
    local function tacticalAnalysis()
        -- need to implement
    end
    
    local function psychologicalManipulation()
        -- need to implement
    end
    
    local abilitiesFolder = Instance.new("Folder")
    abilitiesFolder.Name = "AyanokojiAwakeningAbilities"
    abilitiesFolder.Parent = character
    
    local analysisScript = Instance.new("Script")
    analysisScript.Name = "TacticalAnalysis"
    analysisScript.Parent = abilitiesFolder
    
    local manipulationScript = Instance.new("Script")
    manipulationScript.Name = "PsychologicalManipulation"
    manipulationScript.Parent = abilitiesFolder
    
    wait(30)
    
    aura:Destroy()
    humanoid.WalkSpeed = humanoid.WalkSpeed / 1.3
    humanoid.JumpPower = humanoid.JumpPower / 1.2
    abilitiesFolder:Destroy()
end

AwakeningEvent.OnServerEvent:Connect(activateAwakening)
